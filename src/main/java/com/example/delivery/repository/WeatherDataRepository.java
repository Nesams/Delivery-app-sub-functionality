package com.example.delivery.repository;

import com.example.delivery.entity.WeatherData;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WeatherDataRepository extends CrudRepository<WeatherData, Long> {
    @Query("SELECT w FROM WeatherData w WHERE w.stationName = ?1 and w.observationTime = (SELECT MAX(w2.observationTime) FROM WeatherData w2 WHERE w2.stationName = ?1)")
    WeatherData findByName(String name);
}
