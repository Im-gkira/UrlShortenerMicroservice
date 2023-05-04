package com.microservice.analyticsService.Controller;

import com.microservice.analyticsService.DTO.UrlResponse;
import com.microservice.analyticsService.Model.AnalyticsModel;
import com.microservice.analyticsService.Service.AnalyticsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnalyticsModel> getAnalytics() {
        return analyticsService.getAnalytics();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewUrl(@RequestBody UrlResponse urlResponse) {
        analyticsService.addNewUrl(urlResponse);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateClick(@RequestBody UrlResponse urlResponse) throws Exception {
        log.info("Request to aa rahi hai bro!!!!");
        analyticsService.updateClick(urlResponse);
    }
}
