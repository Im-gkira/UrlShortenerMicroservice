package com.microservice.analyticsService.Service;

import com.microservice.analyticsService.Model.AnalyticsModel;
import com.microservice.analyticsService.Repository.AnalyticsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    public List<AnalyticsModel> getAnalytics(String id){
        return analyticsRepository.findAll().stream().toList();
    }
}
