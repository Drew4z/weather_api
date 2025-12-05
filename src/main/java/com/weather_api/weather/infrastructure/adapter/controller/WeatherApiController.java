package com.weather_api.weather.infrastructure.adapter.controller;

import com.weather_api.weather.application.port.in.WeatherApiUseCase;
import com.weather_api.weather.domain.model.Coordinates;
import com.weather_api.weather.domain.model.Weather;
import com.weather_api.weather.infrastructure.mapper.Mapper;
import com.weather_api.weather.infrastructure.request.OpenWeatherRequest;
import com.weather_api.weather.infrastructure.response.GeoapifyResponse;
import com.weather_api.weather.infrastructure.response.OpenWeatherResponse;
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

    @GetMapping("/api/v1/coordinates")
    public ResponseEntity<GeoapifyResponse> getCoordinates(@RequestParam String city) {
        try {
            Coordinates coordinates = weatherApiUseCase.execute(city);

            GeoapifyResponse response = mapper.GeoDomainToResponse(coordinates);
            return ResponseEntity.status(HttpStatusCode.valueOf(200))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500))
                    .body(null);

        }
    }

    @GetMapping("api/v1/weather")
    public ResponseEntity<OpenWeatherResponse> getWeather(@RequestParam Double lon, @RequestParam Double lat) {
        try {
            OpenWeatherRequest request = new OpenWeatherRequest(lon, lat);
            Coordinates coordinates = mapper.WeatherRequestToDomain(request);

            Weather weather = weatherApiUseCase.execute(coordinates);

            OpenWeatherResponse response = mapper.WeatherDomainToResponse(weather);
            return ResponseEntity.status(HttpStatusCode.valueOf(200))
                    .body(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
