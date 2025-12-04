package com.weather_api.weather.domain.model;

public record Weather(
        String city,
        Double temperature,
        String description,
        String iconUrl
) {
}
