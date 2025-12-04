package com.weather_api.weather.infrastructure.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherResponseDTO(
        String city,
        Double temperature,
        String description,
        String iconUrl
) {
}
