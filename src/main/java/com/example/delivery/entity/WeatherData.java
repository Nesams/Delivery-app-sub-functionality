package com.example.delivery.entity;


import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "weather_data")
@Getter @Setter @NoArgsConstructor
public class WeatherData {
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

}
