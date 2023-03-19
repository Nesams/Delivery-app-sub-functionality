package com.example.delivery;

import com.example.delivery.controller.DeliveryFeeController;
import com.example.delivery.repository.WeatherDataRepository;
import com.example.delivery.service.DeliveryFeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryApplicationTests {
	@Autowired
	private DeliveryFeeService deliveryFeeService;

	@Autowired
	private WeatherDataRepository weatherDataRepository;

	@Autowired
	private DeliveryFeeController deliveryFeeController;


	@Test
	void testCalculateDeliveryFeeWithValidValues() throws Exception {
		String stationName = "Tartu";
		String vehicleType = "Bike";
		Double airTemperature = -2.1;
		Double windSpeed = 4.7;
		String weatherPhenomenon = "Light snow shower";

		Double result = deliveryFeeService.calculateDeliveryFee(stationName, vehicleType, airTemperature, windSpeed, weatherPhenomenon);
		assertEquals(4.0, result);
	}

	@Test
	void testCurrentData() throws Exception {
		String stationName = "Tallinn";
		String vehicleType = "bike";
		Thread.sleep(2000);
		Double result = deliveryFeeController.calculateDeliveryFee(stationName, vehicleType).getBody();
		assertEquals(3.0, result);
	}

	@Test
	void testGetAirTemperatureWithValidValue() throws InterruptedException {
		String stationName = "Tallinn";
		Thread.sleep(2000);
		Double result = deliveryFeeService.getAirTemperature(stationName);
		assertNotNull(result);
	}

	@Test
	void testGetWindSpeedWithValidValue() throws InterruptedException {
		String stationName = "Tallinn";
		Thread.sleep(2000);
		Double result = deliveryFeeService.getWindSpeed(stationName);
		assertNotNull(result);
	}

	@Test
	void testGetWeatherPhenomenonWithValidValue() throws InterruptedException {
		String stationName = "Tallinn";
		Thread.sleep(2000);
		String result = deliveryFeeService.getWeatherPhenomenon(stationName);
		assertNotNull(result);
	}

	@Test
	void testCalculateDeliveryFeeWithInvalidValues() {
		String stationName = "Tallinn";
		String vehicleType = "Bike";
		Double airTemperature = -15.0;
		Double windSpeed = 25.0;
		String weatherPhenomenon = "Hail";

		assertThrows(Exception.class, () -> deliveryFeeService.calculateDeliveryFee(stationName, vehicleType, airTemperature, windSpeed, weatherPhenomenon));
	}
	@Test
	void testCalculateDeliveryFeeWithMaxValues() throws Exception {
		String stationName = "Tallinn";
		String vehicleType = "Bike";
		Double airTemperature = -15.0;
		Double windSpeed = 20.0;
		String weatherPhenomenon = "Heavy rain";
		Double result = deliveryFeeService.calculateDeliveryFee(stationName, vehicleType, airTemperature, windSpeed, weatherPhenomenon);

		assertEquals(5.0, result);
	}

}

