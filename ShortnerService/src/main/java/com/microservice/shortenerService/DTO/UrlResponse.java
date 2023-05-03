package com.microservice.shortenerService.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlResponse {
    private String tinyUrl;
}
