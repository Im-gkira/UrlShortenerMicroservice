package com.microservice.analyticsService.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlResponse {
    private String tinyUrl;
}
