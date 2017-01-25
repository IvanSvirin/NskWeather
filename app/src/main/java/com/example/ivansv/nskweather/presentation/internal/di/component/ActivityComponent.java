package com.example.ivansv.nskweather.presentation.internal.di.component;

import android.app.Activity;

import com.example.ivansv.nskweather.presentation.internal.di.PerActivity;
import com.example.ivansv.nskweather.presentation.internal.di.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
interface ActivityComponent {

    Activity activity();
}
