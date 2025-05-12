package com.exercise.WeatherAlertScheduler.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "weather_alert")
public class WeatherAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private Double temperature;
    private Double humidity;
    private String alertLevel;
    private LocalDateTime timestamp;

    public WeatherAlert(String location, Double temperature, Double humidity, String alertLevel, LocalDateTime timestamp) {
        this.location = location;
        this.temperature = temperature;
        this.humidity = humidity;
        this.alertLevel = alertLevel;
        this.timestamp = timestamp;
    }
}
