package com.weather_api.weather.domain.service;

import com.weather_api.weather.application.port.outh.WeatherApiPortOut;
import com.weather_api.weather.infrastructure.response.WeatherApiResponse;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class WeatherApiService {
    private final WeatherApiPortOut weatherApiPortOut;

    public WeatherApiService (WeatherApiPortOut weatherApiPortOut){
        this.weatherApiPortOut = weatherApiPortOut;
    }

    public WeatherApiResponse execute(String city){
        Geolocation geo = weatherApiPortOut.getCoordenates(city);
        WeatherApiResponse weatherApiDTO = weatherApiPortOut.getWeather(geo.getLat(),geo.getLon());

        return weatherApiDTO;
    }

}
