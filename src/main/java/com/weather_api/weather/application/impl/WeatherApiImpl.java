package com.weather_api.weather.application.impl;

import com.weather_api.weather.application.port.in.WeatherApiUseCase;
import com.weather_api.weather.domain.model.Coordinates;
import com.weather_api.weather.domain.model.Weather;
import com.weather_api.weather.domain.service.GeoapifyService;

import com.weather_api.weather.domain.service.OpenWeatherService;
import org.springframework.stereotype.Service;

@Service
public class WeatherApiImpl implements WeatherApiUseCase {
    private final GeoapifyService weatherApiService;
    private final OpenWeatherService openWeatherService;

    public WeatherApiImpl(GeoapifyService weatherApiService, OpenWeatherService openWeatherService) {
        this.weatherApiService = weatherApiService;
        this.openWeatherService = openWeatherService;
    }

    @Override
    public Coordinates execute(String city) {
        return weatherApiService.execute(city);
    }

    @Override
    public Weather execute(Double lon, Double lat){
        return openWeatherService.execute(lon,lat);
    }
}
