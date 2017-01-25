package com.example.ivansv.nskweather.presentation.internal.di.component;

import com.example.ivansv.nskweather.presentation.internal.di.PerActivity;
import com.example.ivansv.nskweather.presentation.internal.di.module.ActivityModule;
import com.example.ivansv.nskweather.presentation.internal.di.module.ForecastModule;
import com.example.ivansv.nskweather.presentation.view.fragment.ForecastDetailsFragment;
import com.example.ivansv.nskweather.presentation.view.fragment.ForecastListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, ForecastModule.class})
public interface ForecastComponent extends ActivityComponent {
    void inject(ForecastListFragment forecastListFragment);
    void inject(ForecastDetailsFragment forecastDetailsFragment);
}
