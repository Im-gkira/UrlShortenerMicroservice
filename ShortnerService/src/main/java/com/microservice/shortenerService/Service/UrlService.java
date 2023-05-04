package com.microservice.shortenerService.Service;

import com.microservice.shortenerService.DTO.UrlRequest;
import com.microservice.shortenerService.DTO.UrlResponse;
import com.microservice.shortenerService.Model.UrlDataModel;
import com.microservice.shortenerService.Repository.UrlRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Getter
@Setter
@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private final int HASH_LENGTH = 5;
    private final Base64.Encoder base64 = Base64.getUrlEncoder();
    private final WebClient.Builder webClient;

    private final String analyticsUrl = "http://analytics-service/api/analytics";

    @Transactional
    public UrlResponse generateTinyURL(UrlRequest urlRequest) {
        Optional<UrlDataModel> dataModel = urlRepository.findByOrgURL(urlRequest.getOrgURL());
        if (dataModel.isPresent()) {
            log.info(dataModel.get().getOrgURL());
            return UrlResponse.builder().tinyUrl(dataModel.get().getTinyURL()).build();
        } else if (isValidUrl(urlRequest.getOrgURL())) {

            String tinyUrl = encodeURL(urlRequest.getOrgURL());

            UrlDataModel urlDataModel = UrlDataModel.builder()
                    .orgURL(urlRequest.getOrgURL())
                    .tinyURL(tinyUrl)
                    .build();

            webClient.build().post().uri(analyticsUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(UrlResponse.builder().tinyUrl(tinyUrl).build()))
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class));

            urlRepository.save(urlDataModel);
            return UrlResponse.builder().tinyUrl(tinyUrl).build();
        } else {
            log.info("URL is invalid");
            throw new InvalidParameterException("Invalid URL");
        }

    }


    public String encodeURL(String url) {
        try {
            // Compute SHA-256 hash of long URL
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(url.getBytes());

            // Take first 8 bytes of hash as the unique identifier
            byte[] idBytes = new byte[HASH_LENGTH];
            System.arraycopy(hash, 0, idBytes, 0, HASH_LENGTH);

            // Encode identifier using Base64 encoding
            return base64.encodeToString(idBytes);
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            log.error("Error: " + e);
            return null;
        }
    }

    public static boolean isValidUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }


}
