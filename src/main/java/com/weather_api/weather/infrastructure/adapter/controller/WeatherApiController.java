package com.weather_api.weather.infrastructure.adapter.controller;

import com.weather_api.weather.application.port.in.WeatherApiUseCase;
import com.weather_api.weather.domain.model.Coordinates;
import com.weather_api.weather.infrastructure.mapper.Mapper;
import com.weather_api.weather.infrastructure.response.GeoapifyResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class WeatherApiController {
    private final WeatherApiUseCase weatherApiUseCase;
    private final Mapper mapper;

    public WeatherApiController(WeatherApiUseCase weatherApiUseCase, Mapper mapper) {
        this.weatherApiUseCase = weatherApiUseCase;
        this.mapper = mapper;
    }

    @GetMapping("/api/v1/weather")
    public ResponseEntity<GeoapifyResponse> getWeather(@RequestParam String city) {
        try {
            Coordinates coordinates = weatherApiUseCase.execute(city);

            GeoapifyResponse response = mapper.DomainToResponse(coordinates);
            return ResponseEntity.status(HttpStatusCode.valueOf(200))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500))
                    .body(null);

        }
    }

    // @GetMapping("api/v1/weather?lat={lat}?lon={lon}")
    // public ResponseEntity<>

}
