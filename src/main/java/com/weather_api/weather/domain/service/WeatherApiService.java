package com.weather_api.weather.domain.service;

import com.weather_api.weather.application.port.outh.GeoPortOut;
import com.weather_api.weather.domain.model.Coordinates;
import org.springframework.stereotype.Service;

@Service
public class WeatherApiService {
    private final GeoPortOut weatherApiPortOut;

    public WeatherApiService(GeoPortOut weatherApiPortOut) {
        this.weatherApiPortOut = weatherApiPortOut;
    }

    public Coordinates execute(String city) {
        return weatherApiPortOut.getCoordinates(city);
    }
}
