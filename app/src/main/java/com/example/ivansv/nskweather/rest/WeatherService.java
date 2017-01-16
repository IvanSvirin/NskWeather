package com.example.ivansv.nskweather.rest;

import com.example.ivansv.nskweather.model.Weather;

import retrofit2.http.GET;
import rx.Observable;

public interface WeatherService {
    @GET("29634_1.xml")
    Observable<Weather> getWeather();
}
