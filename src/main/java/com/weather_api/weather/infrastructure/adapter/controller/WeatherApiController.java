package com.weather_api.weather.infrastructure.adapter.controller;

import com.weather_api.weather.domain.service.WeatherApiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/weather")
public class WeatherApiController {
    private final WeatherApiService weatherApiService;

    @GetMapping("/{id}")
    public WeatherApiService {
        return
    }
}

