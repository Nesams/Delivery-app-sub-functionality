package com.example.delivery.service;

import com.example.delivery.entity.WeatherData;
import com.example.delivery.repository.WeatherDataRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Service
public class WeatherDataImportService {
    @Autowired
    private WeatherDataRepository weatherDataRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryFeeService.class);

    @PostConstruct
    @Scheduled(cron = "1 15 * * * *")
    public void importData() {
        try {
            URL url = new URL("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php");
            InputStream stream = url.openStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList stationList = doc.getElementsByTagName("station");
            for (int i = 0; i < stationList.getLength(); i++) {
                Element station = (Element) stationList.item(i);
                String name = station.getElementsByTagName("name").item(0).getTextContent();
                switch (name) {
                    case "Tallinn-Harku" -> saveWeatherData(station, "Tallinn");
                    case "Tartu-Tõravere" -> saveWeatherData(station, "Tartu");
                    case "Pärnu" -> saveWeatherData(station, "Pärnu");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveWeatherData(Element station, String city) {
        String code = station.getElementsByTagName("wmocode").item(0).getTextContent();
        String phenomenon = station.getElementsByTagName("phenomenon").item(0).getTextContent();
        double temperature = Double.parseDouble(station.getElementsByTagName("airtemperature").item(0).getTextContent());
        double windspeed = Double.parseDouble(station.getElementsByTagName("windspeed").item(0).getTextContent());
        Date observationTime = new Date();
        WeatherData weather = new WeatherData(city, code, phenomenon, temperature, windspeed, observationTime);
        if (weatherDataRepository != null) {
            weatherDataRepository.save(weather);
            LOGGER.info("Weather data imported");
        }
    }

}
