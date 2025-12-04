package com.weather_api.weather.infrastructure.mapper;

import com.weather_api.weather.domain.model.Coordinates;
import com.weather_api.weather.domain.model.Weather;
import com.weather_api.weather.infrastructure.DTO.GeoapifyResponseDTO;
import com.weather_api.weather.infrastructure.DTO.OpenWeatherResponseDTO;
import com.weather_api.weather.infrastructure.request.OpenWeatherRequest;
import com.weather_api.weather.infrastructure.response.GeoapifyResponse;
import com.weather_api.weather.infrastructure.response.OpenWeatherResponse;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public GeoapifyResponse GeoDomainToResponse(Coordinates coordinates) {
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
                result.lon(),
                result.lat());
    }

    public OpenWeatherResponse WeatherDomainToResponse(Weather weather) {
        OpenWeatherResponse response = new OpenWeatherResponse(
                weather.city(),
                weather.temperature(),
                weather.description(),
                weather.iconUrl());
        return response;
    }

    public Weather OpenWeatherToDomain(OpenWeatherResponseDTO response) {
        if (response == null) {
            throw new RuntimeException("No weather found for the given lon and lat");
        }
        return new Weather(
                response.name(),
                response.main().temp(),
                response.weather().get(0).description(),
                response.weather().get(0).icon());

    }

    public Coordinates WeatherRequestToDomain(OpenWeatherRequest request) {
        return new Coordinates(
                request.lon(),
                request.lat()
        );
    }
}
