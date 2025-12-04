package com.weather_api.weather.domain.service;

import com.weather_api.weather.application.port.outh.WeatherApiPortOut;
import com.weather_api.weather.domain.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherService {
    private final WeatherApiPortOut weatherApiPortOut;

    @Value("${weather.api.key}")
    private final String weatherApiKey;

    @Value("${weather.api.url}")
    private final String weatherApiUrl;

    public OpenWeatherService(WeatherApiPortOut weatherApiPortOut, String weatherApiKey, String weatherApiUrl) {
        this.weatherApiPortOut = weatherApiPortOut;
        this.weatherApiKey = weatherApiKey;
        this.weatherApiUrl = weatherApiUrl;
    }

    public String getData(String url){

    }

    public Weather execute(Double lon, Double lat){
        return
    }
}
