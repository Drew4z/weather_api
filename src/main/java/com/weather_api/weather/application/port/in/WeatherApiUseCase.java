package com.weather_api.weather.application.port.in;

import com.weather_api.weather.infrastructure.response.GeolocationResponse;

public interface WeatherApiUseCase {
    GeolocationResponse execute(String city);

}
