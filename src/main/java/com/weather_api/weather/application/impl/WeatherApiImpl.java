package com.weather_api.weather.application.impl;

import com.weather_api.weather.application.port.in.WeatherApiUseCase;
import com.weather_api.weather.domain.service.WeatherApiService;
import com.weather_api.weather.infrastructure.response.WeatherApiResponse;

public class WeatherApiImpl implements WeatherApiUseCase {
    private final WeatherApiService weatherApiService;

    public WeatherApiImpl (WeatherApiService weatherApiService){
        this.weatherApiService = weatherApiService;
    }

    @Override
    public WeatherApiResponse execute(String city){
        return weatherApiService.execute(city);
    }

}
