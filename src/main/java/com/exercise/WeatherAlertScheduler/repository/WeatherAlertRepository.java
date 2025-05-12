package com.exercise.WeatherAlertScheduler.repository;

import com.exercise.WeatherAlertScheduler.model.WeatherAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherAlertRepository extends JpaRepository<WeatherAlert, Long> {
    List<WeatherAlert> findByTimestampAfter(LocalDateTime time);
    void deleteByTimestampBefore(LocalDateTime cutoff);
}
