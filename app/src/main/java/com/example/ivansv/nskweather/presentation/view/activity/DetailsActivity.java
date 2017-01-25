package com.example.ivansv.nskweather.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.ivansv.nskweather.R;
import com.example.ivansv.nskweather.presentation.internal.di.HasComponent;
import com.example.ivansv.nskweather.presentation.internal.di.component.DaggerForecastComponent;
import com.example.ivansv.nskweather.presentation.internal.di.component.ForecastComponent;
import com.example.ivansv.nskweather.presentation.internal.di.module.ForecastModule;
import com.example.ivansv.nskweather.presentation.view.fragment.ForecastDetailsFragment;

public class DetailsActivity extends BaseActivity implements HasComponent<ForecastComponent> {
    public static final String INTENT_EXTRA_PARAM_TOD = "INTENT_PARAM_TOD";
    private static final String INSTANCE_STATE_PARAM_TOD = "STATE_PARAM_TOD";
    private ForecastComponent forecastComponent;
    private String tod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeActivity(savedInstanceState);
        this.buildComponent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INSTANCE_STATE_PARAM_TOD, this.tod);
        }
        super.onSaveInstanceState(outState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.tod = getIntent().getStringExtra(INTENT_EXTRA_PARAM_TOD);
            addFragment(R.id.fragment_container, new ForecastDetailsFragment());
        } else {
            this.tod = savedInstanceState.getString(INSTANCE_STATE_PARAM_TOD);
        }
    }

    private void buildComponent() {
        this.forecastComponent = DaggerForecastComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .forecastModule(new ForecastModule(this.tod))
                .build();
    }

    @Override
    public ForecastComponent getComponent() {
        return forecastComponent;
    }
}
