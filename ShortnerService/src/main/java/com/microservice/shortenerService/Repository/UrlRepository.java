package com.microservice.shortenerService.Repository;

import com.microservice.shortenerService.Model.UrlDataModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<UrlDataModel,String> {

    Optional<UrlDataModel> findByOrgURL(String orgUrl);
}
