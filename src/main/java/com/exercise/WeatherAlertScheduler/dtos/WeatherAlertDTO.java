package com.exercise.WeatherAlertScheduler.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WeatherAlertDTO {
    private Long id;
    private String location;
    private Double temperature;
    private Double humidity;
    private String alertLevel;
    private LocalDateTime timestamp;
}
