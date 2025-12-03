package com.weather_api.weather.infrastructure.DTO;

import java.util.List;

// Clases auxiliares para mapear la respuesta de la API externa
public class OpenWeatherDTOs {

    // Para la respuesta de Geolocalización
    public static class GeoResponse {
        public double lat;
        public double lon;
        // getters/setters si no son públicos...
    }

    // Para la respuesta del Clima
    public static class WeatherApiResponse {
        public MainData main;
        public List<WeatherDesc> weather;
        public String name;

        public static class MainData {
            public double temp;
        }
        public static class WeatherDesc {
            public String description;
            public String icon;
        }
    }
}