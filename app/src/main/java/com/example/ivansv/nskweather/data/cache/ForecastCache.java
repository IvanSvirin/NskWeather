package com.example.ivansv.nskweather.data.cache;

import com.example.ivansv.nskweather.data.model.Forecast;

import java.util.List;

import rx.Observable;

public interface ForecastCache {

    Observable<List<Forecast>> get();
    Observable<Forecast> get(final String forecastTod);

    void save(List<Forecast> forecasts);
    void save(Forecast forecast);

    boolean isCached();
}
