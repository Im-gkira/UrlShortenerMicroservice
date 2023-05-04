package com.microservice.redirectService.Service;

import com.microservice.redirectService.DTO.UrlResponse;
import com.microservice.redirectService.Model.UrlDataModel;
import com.microservice.redirectService.Repository.UrlRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class RedirectService {
    private final UrlRepository urlRepository;
    private static final RedirectView redirectView = new RedirectView();
    private final WebClient.Builder webClient;
    private final String analyticsUrl = "http://analytics-service/api/analytics";

    public RedirectView redirectTo(String hash) throws Exception {
        Optional<UrlDataModel> urlDataModel = urlRepository.findByTinyURL(hash);

        if (urlDataModel.isPresent()) {
//            log.info(urlDataModel.get().getOrgURL());

            webClient.build().put().uri(analyticsUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(UrlResponse.builder().tinyUrl(hash).build()))
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class));
            redirectView.setUrl(urlDataModel.get().getOrgURL());
            return redirectView;
        } else {
            log.error("No Url found");
            throw new Exception("Not found");
        }
    }
}
