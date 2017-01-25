package com.example.ivansv.nskweather.presentation.internal.di.module;

import android.content.Context;

import com.example.ivansv.nskweather.data.cache.ForecastCache;
import com.example.ivansv.nskweather.data.cache.ForecastCacheImpl;
import com.example.ivansv.nskweather.data.executor.JobExecutor;
import com.example.ivansv.nskweather.data.repository.ForecastDataRepository;
import com.example.ivansv.nskweather.domain.executor.PostExecutionThread;
import com.example.ivansv.nskweather.domain.executor.ThreadExecutor;
import com.example.ivansv.nskweather.domain.repository.ForecastRepository;
import com.example.ivansv.nskweather.presentation.App;
import com.example.ivansv.nskweather.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    ForecastRepository provideForecastRepository(ForecastDataRepository forecastDataRepository) {
        return forecastDataRepository;
    }

    @Provides
    @Singleton
    ForecastCache provideForecastCache(ForecastCacheImpl forecastCache) {
        return forecastCache;
    }
}
