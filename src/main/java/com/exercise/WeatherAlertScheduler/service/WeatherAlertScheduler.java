package com.exercise.WeatherAlertScheduler.service;

import com.exercise.WeatherAlertScheduler.model.WeatherAlert;
import com.exercise.WeatherAlertScheduler.repository.WeatherAlertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class WeatherAlertScheduler {

    private final WeatherAlertRepository weatherAlertRepository;

    public WeatherAlertScheduler(WeatherAlertRepository weatherAlertRepository) {
        this.weatherAlertRepository = weatherAlertRepository;
    }

    @Scheduled(fixedRate = 1800000)                    //In milliseconds
    public void fetchAndStoreAlerts() {
        try {
            List<WeatherAlert> alerts = WeatherAlertService.fetchMockWeatherData();
            weatherAlertRepository.saveAll(alerts);
            log.info("Saved {} weather alerts at {}", alerts.size(), LocalDateTime.now());
        } catch (Exception e) {
            log.error("Error in fetchAndStoreAlerts: {}",e.getMessage(),e);
            //The last argument (e), it tells SLF4J to log the full stack trace of the exception.
        }
    }

    @Scheduled(cron = "0 0 0 * * *")                   //second, minute, hour, day, month, day of week
    public void purgeOldAlerts() {
        try {
            LocalDateTime cutOff = LocalDateTime.now().minusDays(3);
            weatherAlertRepository.deleteByTimestampBefore(cutOff);
        } catch (Exception e){
            log.error("Error in purgeOldAlerts: {}", e.getMessage(), e);
        }
    }
}
