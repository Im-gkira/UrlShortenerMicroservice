package com.microservice.shortenerService.Controller;

import com.microservice.shortenerService.DTO.UrlRequest;
import com.microservice.shortenerService.DTO.UrlResponse;
import com.microservice.shortenerService.Service.UrlService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/api/shortener")
public class UrlController {
    private UrlService urlService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UrlResponse generateTinyURL(@RequestBody UrlRequest urlRequest){
        return urlService.generateTinyURL(urlRequest);
    }
}
