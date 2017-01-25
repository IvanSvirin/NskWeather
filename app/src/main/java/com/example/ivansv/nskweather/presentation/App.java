package com.example.ivansv.nskweather.presentation;

import android.app.Application;

import com.example.ivansv.nskweather.presentation.internal.di.component.ApplicationComponent;
import com.example.ivansv.nskweather.presentation.internal.di.component.DaggerApplicationComponent;
import com.example.ivansv.nskweather.presentation.internal.di.module.ApplicationModule;

public class App extends Application {
    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.buildComponent();
    }

    private void buildComponent() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
