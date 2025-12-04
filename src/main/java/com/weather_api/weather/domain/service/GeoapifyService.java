package com.weather_api.weather.domain.service;

import com.weather_api.weather.application.port.outh.WeatherApiPortOut;
import com.weather_api.weather.domain.model.Coordinates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeoapifyService {
    private final WeatherApiPortOut weatherApiPortOut;
    @Value("${geolocation.api.key}")
    private final String geoApiKey;

    @Value("${geolocation.api.url}")
    private final String geoApiUrl;

    public GeoapifyService(WeatherApiPortOut weatherApiPortOut, String geoApiKey, String geoApiUrl) {
        this.weatherApiPortOut = weatherApiPortOut;
        this.geoApiKey = geoApiKey;
        this.geoApiUrl = geoApiUrl;
    }


    public Coordinates execute(String city) {

        return weatherApiPortOut.getCoordinates(city);
    }


    public String getData(String url){
        return
    }



}
