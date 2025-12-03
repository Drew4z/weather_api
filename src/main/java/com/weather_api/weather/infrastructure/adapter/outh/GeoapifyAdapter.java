package com.weather_api.weather.infrastructure.adapter.outh;

import com.weather_api.weather.application.port.outh.GeoPortOut;
import com.weather_api.weather.domain.model.Coordinates;
import com.weather_api.weather.infrastructure.DTO.GeoapifyResponseDTO;
import com.weather_api.weather.infrastructure.mapper.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.lang.String;

@Component
public class GeoapifyAdapter implements GeoPortOut {

    @Value("${geolocation.api.key}")
    private final String geoApiKey;

    @Value("${geolocation.api.url}")
    private final String geoApiUrl;

    private final RestClient restClient;
    private final Mapper mapper;

    public GeoapifyAdapter(RestClient.Builder builder,
            Mapper mapper,
            @Value("${geolocation.api.key}") String geoApiKey,
            @Value("${geolocation.api.url}") String geoApiUrl) {
        this.restClient = builder.baseUrl(geoApiUrl).build();
        this.mapper = mapper;
        this.geoApiKey = geoApiKey;
        this.geoApiUrl = geoApiUrl;
    }

    @Override
    public Coordinates getCoordinates(String city) {
        GeoapifyResponseDTO response = restClient.get()
                .uri("/v1/geocode/search?text={city}&lang=en&limit=1&type=city&format=json&apiKey={key}", city, geoApiKey)
                .retrieve()
                .body(GeoapifyResponseDTO.class);
        return mapper.GeoapifyToDomain(response);
    }
}
