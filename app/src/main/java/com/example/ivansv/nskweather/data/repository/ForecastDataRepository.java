package com.example.ivansv.nskweather.data.repository;

import com.example.ivansv.nskweather.data.model.Forecast;
import com.example.ivansv.nskweather.data.repository.datasource.ForecastDataStoreFactory;
import com.example.ivansv.nskweather.domain.repository.ForecastRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class ForecastDataRepository implements ForecastRepository {

    private final ForecastDataStoreFactory forecastDataStoreFactory;

    @Inject
    ForecastDataRepository(ForecastDataStoreFactory forecastDataStoreFactory) {
        this.forecastDataStoreFactory = forecastDataStoreFactory;
    }

    @Override
    public Observable<List<Forecast>> forecasts() {
        return this.forecastDataStoreFactory.createList().forecastList();
    }

    @Override
    public Observable<Forecast> forecast(String tod) {
        return this.forecastDataStoreFactory.create(tod).forecastDetails(tod);
    }
}
