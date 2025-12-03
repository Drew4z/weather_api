package com.weather_api.weather.infrastructure.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoapifyResponseDTO(List<Result> results) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Result(Double lon, Double lat) {}
}
