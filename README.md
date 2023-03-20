# Delivery app sub-functionality

## Overview

This backend sub-functionality is a Spring Boot application that calculates the delivery fee based on the vehicle type and city. The application uses a H2 database to retrieve weather data and calculate the delivery fee.


## Architecture
The application consists of the following components:

* Controller class: DeliveryFeeController
* Entity class: WeatherData
* Repository interface: WeatherDataRepository
* Service classes: DeliveryFeeService and WeatherDataImportService
* Application class: DeliveryApplication
* Tests: DeliveryApplicationTests

The Controller class handles incoming requests from the frontend and returns responses. The Model class represents the weather data retrieved from the database. The Repository interface defines the methods for accessing the weather data in the database. The Service classes handle the business logic of calculating the delivery fee and importing weather data. The Application class is the entry point of the application. The Tests ensure the correctness of the application's behavior.

## Technology Stack
The backend sub-functionality is implemented using the following technologies:

* Java 17
* Spring Boot
* Gradle - Kotlin
* H2

## Running application and tests
* Clone the repository to your local machine
* Make sure you have Java 17 and Gradle installed
* Run the Application class / test class

## Endpoint
The application exposes the following endpoint:

GET /delivery-fee: Returns the delivery fee based on the vehicle type and city. Requires the following query parameters:
* vehicleType: The type of vehicle used for delivery (car, bike, or scooter)
* city: The city where the delivery is made

## Importing weather data from Riigi Ilmateenistus
ilmateenistus.ee

Database has 1 table with 7 columns:
* Id(Long)
* Name_of_the_staion(String)
* Wmo_code(String)
* Air_temperature(Double)
* Wind_speed(Double)
* Wather_phenomenon(String)
* Observation_time(Date)

Import task is sceduled with Cron Job, to import data as soon as the application is started and then once every hour, 15 minutes after a full hour.
