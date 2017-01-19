package com.example.ivansv.nskweather.rest;

import com.example.ivansv.nskweather.model.Forecast;
import com.example.ivansv.nskweather.model.Weather;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataProvider {

    private static Observable<Weather> getWeather() {
        WeatherService service = ServiceFactory.createRetrofitService(WeatherService.class);
        return service.getWeather();
    }

    public static Observable<List<Forecast>> getForecastsFromApiObservable() {
        return getWeather()
                .flatMap(weather -> {
                    return Observable.just(weather.getReport().getTown().getForecastList());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
