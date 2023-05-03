package com.microservice.analyticsService.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "analytics")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsModel {

    @Id
    private String id;
    @Indexed(unique = true)
    private String urlId;
    private LocalDateTime timestamp;
    private long clicks = 30L;

}
