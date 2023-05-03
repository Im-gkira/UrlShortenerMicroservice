package com.microservice.analyticsService.Controller;

import com.microservice.analyticsService.Model.AnalyticsModel;
import com.microservice.analyticsService.Service.AnalyticsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnalyticsModel> getAnalytics(@PathVariable String id){
        return analyticsService.getAnalytics(id);
    }
}
