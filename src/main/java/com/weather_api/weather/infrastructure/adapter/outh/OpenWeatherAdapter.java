package com.weather_api.weather.infrastructure.adapter.outh;

import com.weather_api.weather.application.port.outh.WeatherApiPortOut;
import com.weather_api.weather.infrastructure.DTO.OpenWeatherDTOs;
import com.weather_api.weather.infrastructure.response.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherAdapter implements WeatherApiPortOut {

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public OpenWeatherDTOs.GeoResponse getCoordinates(String city) {
        // 1. Llamada a Geo API
        String url = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s", city, apiKey);
        var response = restTemplate.getForObject(url, OpenWeatherDTOs.GeoResponse[].class);

        if (response != null && response.length > 0) {
            return response[0];
        }
        throw new RuntimeException("Ciudad no encontrada");
    }

    @Override
    public WeatherApiResponse getWeather(double lat, double lon) {
        // 2. Llamada a Weather API
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=%s", lat, lon, apiKey);
        var apiData = restTemplate.getForObject(url, OpenWeatherDTOs.WeatherApiResponse.class);

        // 3. Convertir a nuestro DTO limpio
        String iconUrl = "https://openweathermap.org/img/wn/" + apiData.weather.get(0).icon + "@2x.png";

        return new WeatherApiResponse(
                apiData.name,
                apiData.main.temp,
                apiData.weather.get(0).description,
                iconUrl
        );
    }

}
