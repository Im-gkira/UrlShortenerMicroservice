package com.microservice.shortenerService.Model;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "urlService")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UrlDataModel {

    @Id
    private String id;

    @NonNull
    private String orgURL;
    @NonNull
    @Indexed(unique = true)
    private String tinyURL;
    private LocalDateTime createdAt;
}
