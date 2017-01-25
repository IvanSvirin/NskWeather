package com.example.ivansv.nskweather.data.repository.datasource;

import com.example.ivansv.nskweather.data.model.Forecast;

import java.util.List;

import rx.Observable;

public interface ForecastDataStore {

    Observable<List<Forecast>> forecastList();

    Observable<Forecast> forecastDetails(final String forecastTod);
}
