package com.weather_api.weather.infrastructure.adapter.outh;

import com.weather_api.weather.application.port.outh.WeatherApiPortOut;
import com.weather_api.weather.infrastructure.DTO.OpenWeatherDTOs;
import com.weather_api.weather.infrastructure.response.GeolocationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherAdapter implements WeatherApiPortOut {

    @Value("${geolocation.api.key}")
    private String geolocationApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public GeolocationResponse getCoordinates(String city) {
        // 1. Llamada a Geo API
        String url = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s", city, apiKey);
        var response = restTemplate.getForObject(url, OpenWeatherDTOs.GeoResponse[].class);

        if (response != null && response.length > 0) {
            return response[0];
        }
        throw new RuntimeException("Ciudad no encontrada");
    }

}
