package com.weather_api.weather.domain.service;

import com.weather_api.weather.application.port.outh.WeatherApiPortOut;
import com.weather_api.weather.domain.model.Coordinates;
import org.springframework.stereotype.Service;

@Service
public class GeoapifyService {
    private final WeatherApiPortOut weatherApiPortOut;

    public GeoapifyService(WeatherApiPortOut weatherApiPortOut) {
        this.weatherApiPortOut = weatherApiPortOut;
    }

    public Coordinates execute(String city) {
        return weatherApiPortOut.getCoordinates(city);
    }

}
