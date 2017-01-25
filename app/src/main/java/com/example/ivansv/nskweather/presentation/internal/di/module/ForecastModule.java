package com.example.ivansv.nskweather.presentation.internal.di.module;


import com.example.ivansv.nskweather.domain.executor.PostExecutionThread;
import com.example.ivansv.nskweather.domain.executor.ThreadExecutor;
import com.example.ivansv.nskweather.domain.interactor.GetForecastDetails;
import com.example.ivansv.nskweather.domain.interactor.GetForecastList;
import com.example.ivansv.nskweather.domain.interactor.UseCase;
import com.example.ivansv.nskweather.domain.repository.ForecastRepository;
import com.example.ivansv.nskweather.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ForecastModule {
    private String forecastTod = "";

    public ForecastModule() {
    }

    public ForecastModule(String forecastTod) {
        this.forecastTod = forecastTod;
    }

    @Provides
    @PerActivity
    @Named("forecastList")
    UseCase provideGetForecastListUseCase(GetForecastList getForecastList){
        return getForecastList;
    }

    @Provides
    @PerActivity
    @Named("forecastDetails")
    UseCase provideGetForecastDetailsUseCase(ForecastRepository forecastRepository, ThreadExecutor threadExecutor,
                                            PostExecutionThread postExecutionThread) {
        return new GetForecastDetails(threadExecutor, postExecutionThread, forecastTod, forecastRepository);
    }
}
