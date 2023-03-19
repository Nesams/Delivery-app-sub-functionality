package com.example.delivery.service;

import org.springframework.stereotype.Service;

@Service
public interface DeliveryFeeService {
    Double calculateDeliveryFee(String name, String vehicleType, Double airTemperature, Double windSpeed, String weatherPhenomenon) throws Exception;

    Double getAirTemperature(String name);

    Double getWindSpeed(String name);

    String getWeatherPhenomenon(String name);
}
