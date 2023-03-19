package com.example.delivery.entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "weather_data")
public class WeatherData {
    // default constructor
    public WeatherData() {
    }
    public WeatherData(String stationName, String wmoCode, String weatherPhenomenon, Double airTemperature, Double windSpeed, Date observationTime) {
        this.stationName = stationName;
        this.wmoCode = wmoCode;
        this.weatherPhenomenon = weatherPhenomenon;
        this.airTemperature = airTemperature;
        this.windSpeed = windSpeed;
        this.observationTime = observationTime;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name_of_the_station")
    private String stationName;

    @Column(name = "wmo_code")
    private String wmoCode;

    @Column(name = "air_temperature")
    private Double airTemperature;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "weather_phenomenon")
    private String weatherPhenomenon;

    @Column(name = "observation_time")
    private Date observationTime;

    public String getStationName() {
        return stationName;
    }

    public String getWmoCode() {
        return wmoCode;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public String getWeatherPhenomenon() {
        return weatherPhenomenon;
    }

    public Date getObservationTime() {
        return observationTime;
    }
}
