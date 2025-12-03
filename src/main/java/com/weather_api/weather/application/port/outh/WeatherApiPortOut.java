package com.weather_api.weather.application.port.outh;

import com.weather_api.weather.infrastructure.DTO.OpenWeatherDTOs;
import com.weather_api.weather.infrastructure.response.GeolocationResponse;

public interface WeatherApiPortOut {
   GeolocationResponse getCoordinates(String city);
}
