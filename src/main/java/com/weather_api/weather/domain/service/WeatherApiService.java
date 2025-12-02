package com.weather_api.weather.domain.service;

import com.weather_api.weather.infrastructure.response.WeatherApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherApiService {
    private final WeatherApiResponse weatherApiResponse;

}
