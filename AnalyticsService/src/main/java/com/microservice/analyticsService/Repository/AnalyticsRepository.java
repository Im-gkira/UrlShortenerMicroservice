package com.microservice.analyticsService.Repository;

import com.microservice.analyticsService.Model.AnalyticsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepository extends MongoRepository<AnalyticsModel, String> {
}
