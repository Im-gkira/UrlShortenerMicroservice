package com.microservice.redirectService.Repository;

import com.microservice.redirectService.Model.UrlDataModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<UrlDataModel,String> {
    Optional<UrlDataModel> findByTinyURL(String tinyUrl);
}
