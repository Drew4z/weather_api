package com.weather_api.weather.application.port.outh;

import com.weather_api.weather.infrastructure.DTO.OpenWeatherDTOs;
import com.weather_api.weather.infrastructure.response.WeatherApiResponse;

public interface WeatherApiPortOut {
    OpenWeatherDTOs.GeoResponse getCoordinates(String city);
    WeatherApiResponse getWeather(double lat, double lon);
}
