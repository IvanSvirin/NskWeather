package com.example.ivansv.nskweather.domain.repository;

import com.example.ivansv.nskweather.data.model.Forecast;

import java.util.List;

import rx.Observable;

public interface ForecastRepository {

    Observable<List<Forecast>> forecasts();

    Observable<Forecast> forecast(final String tod);
}
