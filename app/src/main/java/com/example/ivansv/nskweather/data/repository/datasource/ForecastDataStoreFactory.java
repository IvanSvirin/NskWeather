package com.example.ivansv.nskweather.data.repository.datasource;

import android.content.Context;

import com.example.ivansv.nskweather.data.cache.ForecastCache;
import com.example.ivansv.nskweather.data.net.WeatherService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ForecastDataStoreFactory {
    private final Context context;
    private final ForecastCache forecastCache;
    private final WeatherService weatherService;

    @Inject
    ForecastDataStoreFactory(Context context, ForecastCache forecastCache, WeatherService weatherService) {
        this.context = context;
        this.forecastCache = forecastCache;
        this.weatherService = weatherService;
    }

    public ForecastDataStore create(String tod) {
        if (forecastCache.isCached()) {
            return createDiskUserDataStore();
        } else {
            return createCloudDiskDataStore();
        }
    }

    public ForecastDataStore createList() {
        return createCloudDiskDataStore();
    }

    private ForecastDataStore createDiskUserDataStore() {
        return new DiskForecastDataStore(forecastCache);
    }

    private ForecastDataStore createCloudDiskDataStore() {
        return new CloudForecastDataStore(context, weatherService, forecastCache);
    }
}
