package com.example.ivansv.nskweather.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivansv.nskweather.R;
import com.example.ivansv.nskweather.data.model.Forecast;
import com.example.ivansv.nskweather.presentation.internal.di.component.ForecastComponent;
import com.example.ivansv.nskweather.presentation.presenter.ForecastDetailsPresenter;
import com.example.ivansv.nskweather.presentation.view.ForecastDetailsView;
import com.example.ivansv.nskweather.util.Util;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForecastDetailsFragment extends BaseFragment implements ForecastDetailsView {
    @Inject
    ForecastDetailsPresenter forecastDetailsPresenter;
    @Bind(R.id.tv_day_quarter)
    TextView dayQuarter;
    @Bind(R.id.tv_temperature_degree)
    TextView temperatureDegree;
    @Bind(R.id.iv_icon)
    ImageView icon;
    @Bind(R.id.tv_precipitation)
    TextView precipitation;
    @Bind(R.id.tv_real_feel)
    TextView realFeel;
    @Bind(R.id.tv_date)
    TextView date;
    @Bind(R.id.tv_direction_value)
    TextView directionValue;
    @Bind(R.id.tv_speed_value)
    TextView speedValue;
    @Bind(R.id.tv_pressure_value)
    TextView pressureValue;
    @Bind(R.id.tv_wetness_value)
    TextView wetnessValue;

    public ForecastDetailsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(ForecastComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.forecastDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadProductDetails();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.forecastDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.forecastDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.forecastDetailsPresenter.destroy();
    }

    @Override
    public void renderForecast(Forecast forecast) {
        if (forecast != null) {
            this.dayQuarter.setText(Util.getDayTime(forecast.getTod()).toUpperCase());
            char degree = 0x00B0;
            this.temperatureDegree.setText(Util.getAverage(forecast.getTemperature().getMin(), forecast.getTemperature().getMax())
                    .concat(String.valueOf(degree)));
            this.icon.setImageResource(Util.getIconBig(forecast.getTod(), forecast.getPhenomena().getCloudiness(),
                    forecast.getPhenomena().getPrecipitation(), forecast.getPhenomena().getSpower()));
            this.precipitation.setText(Util.getPhenomena(forecast.getPhenomena().getCloudiness(), forecast.getPhenomena().getPrecipitation()));
            this.realFeel.setText(getString(R.string.real_feel).concat(Util.getAverage(forecast.getHeat().getMin(), forecast.getHeat().getMax()))
                    .concat(String.valueOf(degree)));
            this.date.setText(forecast.getDay().concat(getString(R.string.slash)).concat(forecast.getMonth()).concat(getString(R.string.space))
                    .concat(getResources().getStringArray(R.array.week_day)[Integer.parseInt(forecast.getWeekday())]));
            this.directionValue.setText(Util.getDirection(forecast.getWind().getDirection()));
            this.speedValue.setText(Util.getAverage(forecast.getWind().getMin(), forecast.getWind().getMax()).concat(getString(R.string.speed_unit)));
            this.pressureValue.setText(Util.getAverage(forecast.getPressure().getMin(), forecast.getPressure().getMax())
                    .concat(getString(R.string.pressure_unit)));
            this.wetnessValue.setText(Util.getAverage(forecast.getRelwet().getMin(), forecast.getRelwet().getMax())
                    .concat(getString(R.string.percent)));
        }
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void showRetry() {
    }

    @Override
    public void hideRetry() {
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    private void loadProductDetails() {
        if (this.forecastDetailsPresenter != null) {
            this.forecastDetailsPresenter.initialize();
        }
    }
}
