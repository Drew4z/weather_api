package com.weather_api.weather.application.port.outh;

import com.weather_api.weather.domain.model.Coordinates;
import com.weather_api.weather.domain.model.Weather;

public interface WeatherApiPortOut {
   Coordinates getCoordinates(String city);

   Weather getWeather(Coordinates coordinates);
}
