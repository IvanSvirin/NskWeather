package com.example.ivansv.nskweather.presentation.internal.di.component;

import android.content.Context;

import com.example.ivansv.nskweather.domain.executor.PostExecutionThread;
import com.example.ivansv.nskweather.domain.executor.ThreadExecutor;
import com.example.ivansv.nskweather.domain.repository.ForecastRepository;
import com.example.ivansv.nskweather.presentation.internal.di.module.ApplicationModule;
import com.example.ivansv.nskweather.presentation.internal.di.module.NetModule;
import com.example.ivansv.nskweather.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    ForecastRepository forecastRepository();
//    Retrofit retrofit();
//    WeatherService weatherService();
}
