package com.example.delivery.controller;

import com.example.delivery.service.impl.DeliveryFeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery-fee")
public class DeliveryFeeController {

    private final DeliveryFeeServiceImpl deliveryFeeService;

    @Autowired
    public DeliveryFeeController(DeliveryFeeServiceImpl deliveryFeeService) {
        this.deliveryFeeService = deliveryFeeService;
    }
    @GetMapping
    public ResponseEntity<Double> calculateDeliveryFee(@RequestParam String stationName,
                                                       @RequestParam String vehicleType) throws Exception {
        Double airTemperature = deliveryFeeService.getAirTemperature(stationName);
        Double windSpeed = deliveryFeeService.getWindSpeed(stationName);
        String weatherPhenomenon = deliveryFeeService.getWeatherPhenomenon(stationName);


        Double deliveryFee = deliveryFeeService.calculateDeliveryFee(stationName, vehicleType, airTemperature, windSpeed, weatherPhenomenon);
        return ResponseEntity.ok(deliveryFee);
    }

}
