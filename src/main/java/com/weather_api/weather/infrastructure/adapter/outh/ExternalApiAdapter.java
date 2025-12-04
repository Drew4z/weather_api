package com.weather_api.weather.infrastructure.adapter.outh;

import com.weather_api.weather.application.port.outh.WeatherApiPortOut;
import com.weather_api.weather.domain.model.Coordinates;
import com.weather_api.weather.domain.model.Weather;
import com.weather_api.weather.infrastructure.DTO.GeoapifyResponseDTO;
import com.weather_api.weather.infrastructure.DTO.OpenWeatherResponseDTO;
import com.weather_api.weather.infrastructure.mapper.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ExternalApiAdapter implements WeatherApiPortOut {

    private final RestClient restClient;
    private final Mapper mapper;

    @Value("${geolocation.api.key}")
    private String geoApiKey;

    @Value("${geolocation.api.url}")
    private String geoApiUrl;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    public ExternalApiAdapter(RestClient.Builder builder, Mapper mapper,
            @Value("${geolocation.api.key}") String geoApiKey,
            @Value("${geolocation.api.url}") String geoApiUrl,
            @Value("${weather.api.key}") String weatherApiKey,
            @Value("${weather.api.url}") String weatherApiUrl) {
        this.restClient = builder.build();
        this.mapper = mapper;
        this.geoApiKey = geoApiKey;
        this.geoApiUrl = geoApiUrl;
        this.weatherApiKey = weatherApiKey;
        this.weatherApiUrl = weatherApiUrl;
    }

    private <T> T makeRequest(String url, Class<T> responseType, Object... uriVariables) {
        return restClient.get()
                .uri(url, uriVariables)
                .retrieve()
                .body(responseType);
    }

    @Override
    public Coordinates getCoordinates(String city) {
        String url = String.format("%s/v1/geocode/search?text={city}&apiKey={apiKey}&format=json", geoApiUrl);
        GeoapifyResponseDTO response = makeRequest(url, GeoapifyResponseDTO.class, city, geoApiKey);
        return mapper.GeoapifyToDomain(response);
    }

    @Override
    public Weather getWeather(Coordinates coordinates) {
        String url = String.format("%s/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}&units=metric", weatherApiUrl);
        OpenWeatherResponseDTO response = makeRequest(url, OpenWeatherResponseDTO.class, coordinates.lat(),
                coordinates.lon(), weatherApiKey);
        return mapper.OpenWeatherToDomain(response);
    }
}
