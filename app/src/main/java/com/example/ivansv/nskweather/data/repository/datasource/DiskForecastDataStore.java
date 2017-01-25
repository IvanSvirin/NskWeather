package com.example.ivansv.nskweather.data.repository.datasource;

import com.example.ivansv.nskweather.data.cache.ForecastCache;
import com.example.ivansv.nskweather.data.model.Forecast;

import java.util.List;

import rx.Observable;

class DiskForecastDataStore implements ForecastDataStore {

    private final ForecastCache forecastCache;

    DiskForecastDataStore(ForecastCache forecastCache) {
        this.forecastCache = forecastCache;
    }

    @Override
    public Observable<List<Forecast>> forecastList() {
        return null;
    }

    @Override
    public Observable<Forecast> forecastDetails(String forecastTod) {
        return this.forecastCache.get(forecastTod);
    }
}
