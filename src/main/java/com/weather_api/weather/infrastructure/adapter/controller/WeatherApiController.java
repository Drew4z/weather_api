package com.weather_api.weather.infrastructure.adapter.controller;

import com.weather_api.weather.application.port.in.WeatherApiUseCase;
import com.weather_api.weather.infrastructure.response.GeolocationResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class WeatherApiController {
    private final WeatherApiUseCase weatherApiUseCase;

    public WeatherApiController(WeatherApiUseCase weatherApiUseCase){
        this.weatherApiUseCase = weatherApiUseCase;
    }

    @GetMapping("api/v1/weather?city={city}")
    public ResponseEntity<GeolocationResponse> getWeather(@PathVariable String city){
        try{
            GeolocationResponse response = weatherApiUseCase.execute(city);
            return ResponseEntity.status(HttpStatusCode.valueOf(200))
                .body(response);
        }catch (Exception e){
        return ResponseEntity.status(HttpStatusCode.valueOf(500))
                .body(null);
        }
    }

//    @GetMapping("api/v1/weather?lat={lat}?lon={lon}")
//    public ResponseEntity<>

}

