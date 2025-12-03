package com.weather_api.weather.infrastructure.response;

public record WeatherResponse(
        String city,
        Double temperature,
        String description,
        String iconUrl
) {
}
