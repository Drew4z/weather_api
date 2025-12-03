package com.weather_api.weather.application.port.in;

import com.weather_api.weather.domain.model.Coordinates;

public interface WeatherApiUseCase {
    Coordinates execute(String city);
}
