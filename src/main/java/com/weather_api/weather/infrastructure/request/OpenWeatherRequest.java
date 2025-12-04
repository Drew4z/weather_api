package com.weather_api.weather.infrastructure.request;

public record OpenWeatherRequest(
        Double lon,
        Double lat
) {
}
