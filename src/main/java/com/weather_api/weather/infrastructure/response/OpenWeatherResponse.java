package com.weather_api.weather.infrastructure.response;

public record OpenWeatherResponse(
        String city,
        Double temperature,
        String description,
        String iconUrl
) {
}
