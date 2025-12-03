package com.weather_api.weather.infrastructure.response;


public record WeatherApiResponse(
        String city,
        Double temperature,
        String description,
        String iconUrl
){}
