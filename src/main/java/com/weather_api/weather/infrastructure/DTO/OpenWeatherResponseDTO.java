package com.weather_api.weather.infrastructure.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherResponseDTO(
                Main main,
                List<Weather> weather,
                String name) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Main(Double temp, Double humidity) {
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Weather(String description, String icon) {
        }
}
