package com.weather_api.weather.infrastructure.adapter.outh;

import com.weather_api.weather.application.port.outh.WeatherApiPortOut;
import com.weather_api.weather.infrastructure.mapper.Mapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.lang.String;

@Component
public class GeoapifyAdapter implements WeatherApiPortOut {

    private final Mapper mapper;
    public GeoapifyAdapter(Mapper mapper){
        this.mapper = mapper;
    }

    @Override
    public String getData(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}
