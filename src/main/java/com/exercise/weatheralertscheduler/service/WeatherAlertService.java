package com.exercise.weatheralertscheduler.service;

import com.exercise.weatheralertscheduler.dtos.WeatherAlertDTO;
import com.exercise.weatheralertscheduler.model.WeatherAlert;
import com.exercise.weatheralertscheduler.repository.WeatherAlertRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherAlertService {

    public final WeatherAlertRepository weatherAlertRepository;

    public WeatherAlertService(WeatherAlertRepository weatherAlertRepository) {
        this.weatherAlertRepository = weatherAlertRepository;
    }

    public static List<WeatherAlert> fetchMockWeatherData() {
        List<WeatherAlert> alerts = new ArrayList<>();
        String[] locations = {"New York", "London", "Tokyo", "Mumbai"};

        for (String loc: locations) {
            double temp = 20 + Math.random() * 20;
            double humidity = 40 + Math.random() * 50;
            String alertLevel = temp > 35 ? "HIGH" : temp > 30 ? "MODERATE" : "LOW";

            WeatherAlert alert = new WeatherAlert();
            alert.setLocation(loc);
            alert.setTemperature(temp);
            alert.setHumidity(humidity);
            alert.setAlertLevel(alertLevel);
            alert.setTimestamp(LocalDateTime.now());

            alerts.add(alert);
        }
        return alerts;
    }

    public List<WeatherAlertDTO> getCurrentAlerts() {
        LocalDateTime cutOff = LocalDateTime.now().minusDays(3);
        List<WeatherAlert> alerts = weatherAlertRepository.findByTimestampAfter(cutOff);
        return alerts.stream()
                .map(this::convertToDto)
                .toList();
    }

    public WeatherAlertDTO convertToDto(WeatherAlert alert) {
        return WeatherAlertDTO.builder()
                .id(alert.getId())
                .location(alert.getLocation())
                .temperature(alert.getTemperature())
                .humidity(alert.getHumidity())
                .alertLevel(alert.getAlertLevel())
                .timestamp(alert.getTimestamp())
                .build();
    }
}
