package com.exercise.WeatherAlertScheduler.controller;

import com.exercise.WeatherAlertScheduler.dtos.WeatherAlertDTO;
import com.exercise.WeatherAlertScheduler.service.WeatherAlertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/alert")
public class WeatherAlertController {

    public WeatherAlertService weatherAlertService;

    @GetMapping("/current")
    public List<WeatherAlertDTO> getCurrentAlerts() {
        return weatherAlertService.getCurrentAlerts();
    }
}
