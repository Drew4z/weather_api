package com.weather_api.weather.application.port.in;

import com.weather_api.weather.infrastructure.response.WeatherApiResponse;

public interface WeatherApiUseCase {
    WeatherApiResponse execute(String city);
}
