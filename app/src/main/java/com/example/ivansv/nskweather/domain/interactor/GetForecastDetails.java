package com.example.ivansv.nskweather.domain.interactor;

import com.example.ivansv.nskweather.domain.executor.PostExecutionThread;
import com.example.ivansv.nskweather.domain.executor.ThreadExecutor;
import com.example.ivansv.nskweather.domain.repository.ForecastRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetForecastDetails extends UseCase {

    private final String forecastTod;
    private final ForecastRepository forecastRepository;

    @Inject
    public GetForecastDetails(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                              String forecastTod, ForecastRepository forecastRepository) {
        super(threadExecutor, postExecutionThread);
        this.forecastTod = forecastTod;
        this.forecastRepository = forecastRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return forecastRepository.forecast(this.forecastTod);
    }
}
