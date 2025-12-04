package com.weather_api.weather.application.port.in;

import com.weather_api.weather.domain.model.Coordinates;
import com.weather_api.weather.domain.model.Weather;

public interface WeatherApiUseCase {
    Coordinates execute(String city);

    Weather execute(Double lon, Double lat);
}
