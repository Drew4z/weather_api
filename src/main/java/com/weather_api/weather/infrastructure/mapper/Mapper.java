package com.weather_api.weather.infrastructure.mapper;

import com.weather_api.weather.domain.model.Coordinates;
import com.weather_api.weather.infrastructure.DTO.GeoapifyResponseDTO;
import com.weather_api.weather.infrastructure.response.GeoapifyResponse;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public GeoapifyResponse DomainToResponse(Coordinates coordinates) {
        GeoapifyResponse response = new GeoapifyResponse(
                coordinates.lon(),
                coordinates.lat());
        return response;
    }

    public Coordinates GeoapifyToDomain(GeoapifyResponseDTO response) {
        if (response.results() == null || response.results().isEmpty()) {
            throw new RuntimeException("No coordinates found for the given city");
        }
        var result = response.results().get(0);
        return new Coordinates(
                result.lat(),
                result.lon());
    }
}
