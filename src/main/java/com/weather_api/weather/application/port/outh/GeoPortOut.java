package com.weather_api.weather.application.port.outh;

import com.weather_api.weather.domain.model.Coordinates;

public interface GeoPortOut {
   Coordinates getCoordinates(String city);
}
