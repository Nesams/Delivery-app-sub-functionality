package com.example.delivery.service.impl;

import com.example.delivery.repository.WeatherDataRepository;
import com.example.delivery.service.DeliveryFeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryFeeServiceImpl implements DeliveryFeeService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    private double getBaseDeliveryFee(String name, String vehicleType) {
        if (name.equals("Tallinn")) {
            if (vehicleType.equalsIgnoreCase("Car")) {
                return 4.0;
            } else if (vehicleType.equalsIgnoreCase("Scooter")) {
                return 3.5;
            } else if (vehicleType.equalsIgnoreCase("Bike")) {
                return 3.0;
            }
        } else if (name.equals("Tartu")) {
            if (vehicleType.equalsIgnoreCase("Car")) {
                return 3.5;
            } else if (vehicleType.equalsIgnoreCase("Scooter")) {
                return 3.0;
            } else if (vehicleType.equalsIgnoreCase("Bike")) {
                return 2.5;
            }
        } else if (name.equalsIgnoreCase("Pärnu")) {
            if (vehicleType.equalsIgnoreCase("Car")) {
                return 3.0;
            } else if (vehicleType.equalsIgnoreCase("Scooter")) {
                return 2.5;
            } else if (vehicleType.equalsIgnoreCase("Bike")) {
                return 2.0;
            }
        }
        return 0.0;
    }

    private double getAirTemperatureEffect(Double airTemperature) {
        double atef = 0.0;
        if (airTemperature != null) {
            if (airTemperature < -10.0) {
                atef = 1.0;
            } else if (airTemperature >= -10.0 && airTemperature < 0.0) {
                atef = 0.5;
            }
        }
        return atef;
    }

    private double getWindSpeedEffect(String vehicleType, Double windSpeed) throws Exception {
        double wsef = 0.0;
        if (vehicleType.equalsIgnoreCase("Bike") && windSpeed != null) {
            if (windSpeed >= 10.0 && windSpeed <= 20.0) {
                wsef = 0.5;
            } else if (windSpeed > 20.0) {
                throw new Exception("Usage of selected vehicle type is forbidden");
            }
        }
        return wsef;
    }

    private double getWeatherPhenomenonEffect(String weatherPhenomenon, String vehicleType) throws Exception {
        double wpef = 0.0;
        if ((vehicleType.equalsIgnoreCase("Scooter") || vehicleType.equalsIgnoreCase("Bike")) && weatherPhenomenon != null) {
            if (weatherPhenomenon.equalsIgnoreCase("Light snow shower") || weatherPhenomenon.equalsIgnoreCase("Moderate snowfall") ||
                    weatherPhenomenon.equalsIgnoreCase("Heavy snowfall") || weatherPhenomenon.equalsIgnoreCase("Sleet")) {
                wpef = 1.0;
            } else if (weatherPhenomenon.equalsIgnoreCase("Light rain") || weatherPhenomenon.equalsIgnoreCase("Moderate rain") ||
                    weatherPhenomenon.equalsIgnoreCase("Heavy rain") || weatherPhenomenon.equalsIgnoreCase("Thunderstorm")) {
                wpef = 0.5;
            } else if (weatherPhenomenon.equalsIgnoreCase("Glaze") || weatherPhenomenon.equalsIgnoreCase("Hail")) {
                throw new Exception("Usage of selected vehicle type is forbidden");
            }
        }
        return wpef;
    }

    @Override
    public Double calculateDeliveryFee(String name, String vehicleType, Double airTemperature, Double windSpeed, String weatherPhenomenon) throws Exception {
        double rbf;
        double atef;
        double wsef;
        double wpef;

        rbf = getBaseDeliveryFee(name, vehicleType);
        atef = getAirTemperatureEffect(airTemperature);
        wsef = getWindSpeedEffect(vehicleType, windSpeed);
        wpef = getWeatherPhenomenonEffect(weatherPhenomenon, vehicleType);
        return rbf + atef + wsef + wpef;
    }

    public Double getAirTemperature(String stationName) {
        return weatherDataRepository.findByName(stationName).getAirTemperature();
    }

    public Double getWindSpeed(String stationName) {
        return weatherDataRepository.findByName(stationName).getWindSpeed();
    }

    public String getWeatherPhenomenon(String stationName) {
        return weatherDataRepository.findByName(stationName).getWeatherPhenomenon();
    }
}
