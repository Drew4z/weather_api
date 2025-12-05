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

// @Component: Indica a Spring que esta clase es un "Bean" gestionado.
// Spring creará una instancia de ella y la inyectará donde se necesite (Inyección de Dependencias).
@Component
public class ExternalApiAdapter implements WeatherApiPortOut {

    // Cliente HTTP moderno de Spring para hacer peticiones (GET, POST, etc.)
    private final RestClient restClient;
    // Mapper para convertir los DTOs (datos de la API) a Modelos de Dominio (datos
    // de tu app)
    private final Mapper mapper;

    // @Value: Inyecta valores desde el archivo application.properties
    @Value("${geolocation.api.key}")
    private String geoApiKey;

    @Value("${geolocation.api.url}")
    private String geoApiUrl;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    // Constructor: Spring usa esto para inyectar las dependencias necesarias.
    public ExternalApiAdapter(RestClient.Builder builder, Mapper mapper,
            @Value("${geolocation.api.key}") String geoApiKey,
            @Value("${geolocation.api.url}") String geoApiUrl,
            @Value("${weather.api.key}") String weatherApiKey,
            @Value("${weather.api.url}") String weatherApiUrl) {
        this.restClient = builder.build(); // Construimos el cliente HTTP
        this.mapper = mapper;
        this.geoApiKey = geoApiKey;
        this.geoApiUrl = geoApiUrl;
        this.weatherApiKey = weatherApiKey;
        this.weatherApiUrl = weatherApiUrl;
    }

    // --- FUNCIÓN GENÉRICA PARA PETICIONES HTTP ---
    // <T>: Declara que este método es genérico. 'T' será el tipo de clase que
    // esperas recibir.
    // Class<T> responseType: Es la clase a la que quieres convertir el JSON
    // recibido (ej: GeoapifyResponseDTO.class).
    // Object... uriVariables: "Varargs" (argumentos variables) para rellenar los
    // huecos en la URL ({city}, {apiKey}, etc.).
    private <T> T makeRequest(String url, Class<T> responseType, Object... uriVariables) {
        return restClient.get() // 1. Prepara una petición GET
                .uri(url, uriVariables) // 2. Configura la URL y sustituye las variables
                .retrieve() // 3. Ejecuta la petición
                .body(responseType); // 4. Convierte el JSON de la respuesta a la clase 'T' automáticamente
    }

    @Override
    public Coordinates getCoordinates(String city) {
        // Preparamos la URL con marcadores {}
        String url = String.format("%s/v1/geocode/search?text={city}&apiKey={apiKey}&format=json", geoApiUrl);

        // Llamamos a nuestra función genérica.
        // Le decimos: "Usa esta URL, y convierte la respuesta a un
        // GeoapifyResponseDTO".
        GeoapifyResponseDTO response = makeRequest(url, GeoapifyResponseDTO.class, city, geoApiKey);

        // Convertimos el DTO (sucio) al modelo de dominio (limpio)
        return mapper.GeoapifyToDomain(response);
    }

    @Override
    public Weather getWeather(Coordinates coordinates) {
        String url = String.format("%s/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}&units=metric",
                weatherApiUrl);

        // Reutilizamos la misma función genérica, pero ahora esperamos un
        // OpenWeatherResponseDTO.
        OpenWeatherResponseDTO response = makeRequest(url, OpenWeatherResponseDTO.class, coordinates.lat(),
                coordinates.lon(), weatherApiKey);

        return mapper.OpenWeatherToDomain(response);
    }
}
