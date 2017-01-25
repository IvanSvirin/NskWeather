package com.example.ivansv.nskweather.presentation.view.activity;

import android.os.Bundle;

import com.example.ivansv.nskweather.R;
import com.example.ivansv.nskweather.presentation.internal.di.HasComponent;
import com.example.ivansv.nskweather.presentation.internal.di.component.DaggerForecastComponent;
import com.example.ivansv.nskweather.presentation.internal.di.component.ForecastComponent;
import com.example.ivansv.nskweather.presentation.view.fragment.StartFragment;

public class MainActivity extends BaseActivity implements HasComponent<ForecastComponent> {
    private ForecastComponent forecastComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buildComponent();
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new StartFragment());
        }
    }

    private void buildComponent() {
        this.forecastComponent = DaggerForecastComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public ForecastComponent getComponent() {
        return forecastComponent;
    }
}
