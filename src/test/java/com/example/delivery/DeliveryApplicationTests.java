package com.example.delivery;

import com.example.delivery.controller.DeliveryFeeController;
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
	void testDeliveryFeeCalculationWithCurrentData() throws Exception {
		String stationName = "Tallinn";
		String vehicleType = "bike";
		Double result = deliveryFeeController.calculateDeliveryFee(stationName, vehicleType).getBody();
		assertEquals(3.0, result);
	}

	@Test
	void testGetAirTemperatureWithValidValue() {
		String stationName = "Tallinn";
		Double result = deliveryFeeService.getAirTemperature(stationName);
		assertNotNull(result);
	}

	@Test
	void testGetWindSpeedWithValidValue() {
		String stationName = "Tallinn";
		Double result = deliveryFeeService.getWindSpeed(stationName);
		assertNotNull(result);
	}

	@Test
	void testGetWeatherPhenomenonWithValidValue() {
		String stationName = "Tallinn";
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

