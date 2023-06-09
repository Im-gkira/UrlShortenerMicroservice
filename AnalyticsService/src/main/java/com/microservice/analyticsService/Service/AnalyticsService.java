package com.microservice.analyticsService.Service;

import com.microservice.analyticsService.DTO.UrlResponse;
import com.microservice.analyticsService.Model.AnalyticsModel;
import com.microservice.analyticsService.Repository.AnalyticsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
@Slf4j
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    public List<AnalyticsModel> getAnalytics(){
        return analyticsRepository.findAll().stream().toList();
    }

    public void addNewUrl(UrlResponse urlResponse){
        log.info(urlResponse.getTinyUrl());
        analyticsRepository.save(AnalyticsModel.builder().urlId(urlResponse.getTinyUrl()).build());
    }

    public void updateClick(UrlResponse urlResponse) throws Exception {
        Optional<AnalyticsModel> data = analyticsRepository.findByUrlId(urlResponse.getTinyUrl());

        if (data.isPresent()){
            analyticsRepository.save(AnalyticsModel.builder()
                    .clicks(data.get().getClicks()+1L)
                    .id(data.get().getId())
                    .urlId(data.get().getUrlId())
                    .timestamp(data.get().getTimestamp())
                    .build());
        }
        else
        {
            throw new Exception("No data entry exist for this url");
        }
    }


}
