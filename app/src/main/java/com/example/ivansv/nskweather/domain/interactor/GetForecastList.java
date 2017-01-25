package com.example.ivansv.nskweather.domain.interactor;

import com.example.ivansv.nskweather.domain.executor.PostExecutionThread;
import com.example.ivansv.nskweather.domain.executor.ThreadExecutor;
import com.example.ivansv.nskweather.domain.repository.ForecastRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetForecastList extends UseCase {
    private final ForecastRepository forecastRepository;

    @Inject
    GetForecastList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                           ForecastRepository forecastRepository) {
        super(threadExecutor, postExecutionThread);
        this.forecastRepository = forecastRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return forecastRepository.forecasts();
    }
}
