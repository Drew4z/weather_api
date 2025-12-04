package com.weather_api.weather.domain.service;

import com.weather_api.weather.application.port.outh.WeatherApiPortOut;
import com.weather_api.weather.domain.model.Weather;
import org.springframework.stereotype.Service;

import com.weather_api.weather.domain.model.Coordinates;

@Service
public class OpenWeatherService {
    private final WeatherApiPortOut weatherApiPortOut;

    public OpenWeatherService(WeatherApiPortOut weatherApiPortOut) {
        this.weatherApiPortOut = weatherApiPortOut;
    }

    public Weather execute(Coordinates coordinates) {
        return weatherApiPortOut.getWeather(coordinates);
    }
}
