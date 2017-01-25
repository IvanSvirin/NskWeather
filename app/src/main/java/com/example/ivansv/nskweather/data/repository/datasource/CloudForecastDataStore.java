package com.example.ivansv.nskweather.data.repository.datasource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.ivansv.nskweather.data.cache.ForecastCache;
import com.example.ivansv.nskweather.data.exception.NetworkConnectionException;
import com.example.ivansv.nskweather.data.model.Forecast;
import com.example.ivansv.nskweather.data.net.WeatherService;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

class CloudForecastDataStore implements ForecastDataStore {
    private final Context context;
    private final WeatherService weatherService;
    private final ForecastCache forecastCache;

    private final Action1<List<Forecast>> saveListToCacheAction = forecasts -> {
        if (forecasts != null) {
            CloudForecastDataStore.this.forecastCache.save(forecasts);
        }
    };

    CloudForecastDataStore(Context context, WeatherService weatherService, ForecastCache forecastCache) {
        this.context = context;
        this.weatherService = weatherService;
        this.forecastCache = forecastCache;
    }

    @Override
    public Observable<List<Forecast>> forecastList() {
        if (isThereInternetConnection()) {
            return this.weatherService.getWeather().flatMap(weather -> {
                return Observable.just(weather.getReport().getTown().getForecastList())
                        .doOnNext(saveListToCacheAction);
            });
        } else {
            return Observable.create(subscriber -> {
                subscriber.onError(new NetworkConnectionException());
            });
        }
    }

    @Override
    public Observable<Forecast> forecastDetails(String forecastTod) {
        return null;
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        return isConnected;
    }
}
