package com.example.ivansv.nskweather.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivansv.nskweather.R;
import com.example.ivansv.nskweather.util.Util;
import com.example.ivansv.nskweather.model.Forecast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    private Forecast forecast;
    @BindView(R.id.tv_day_quarter)
    TextView dayQuarter;
    @BindView(R.id.tv_temperature_degree)
    TextView temperatureDegree;
    @BindView(R.id.iv_icon)
    ImageView icon;
    @BindView(R.id.tv_precipitation)
    TextView precipitation;
    @BindView(R.id.tv_real_feel)
    TextView realFeel;
    @BindView(R.id.tv_date)
    TextView date;
    @BindView(R.id.tv_direction_value)
    TextView directionValue;
    @BindView(R.id.tv_speed_value)
    TextView speedValue;
    @BindView(R.id.tv_pressure_value)
    TextView pressureValue;
    @BindView(R.id.tv_wetness_value)
    TextView wetnessValue;
    private char degree = 0x00B0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        forecast = (Forecast) getIntent().getSerializableExtra("forecast");
        ButterKnife.bind(this);
        setView();
    }

    private void setView() {
        dayQuarter.setText(Util.getDayTime(forecast.getTod()).toUpperCase());
        temperatureDegree.setText(Util.getAverage(forecast.getTemperature().getMin(), forecast.getTemperature().getMax())
                .concat(String.valueOf(degree)));
        icon.setImageResource(Util.getIconBig(forecast.getTod(), forecast.getPhenomena().getCloudiness(),
                forecast.getPhenomena().getPrecipitation(), forecast.getPhenomena().getSpower()));
        precipitation.setText(Util.getPhenomena(forecast.getPhenomena().getCloudiness(), forecast.getPhenomena().getPrecipitation()));
        realFeel.setText(getString(R.string.real_feel).concat(Util.getAverage(forecast.getHeat().getMin(), forecast.getHeat().getMax()))
        .concat(String.valueOf(degree)));
        date.setText(forecast.getDay().concat(getString(R.string.slash)).concat(forecast.getMonth()).concat(getString(R.string.space))
                .concat(getResources().getStringArray(R.array.week_day)[Integer.parseInt(forecast.getWeekday())]));
        directionValue.setText(Util.getDirection(forecast.getWind().getDirection()));
        speedValue.setText(Util.getAverage(forecast.getWind().getMin(), forecast.getWind().getMax()).concat(getString(R.string.speed_unit)));
        pressureValue.setText(Util.getAverage(forecast.getPressure().getMin(), forecast.getPressure().getMax())
                .concat(getString(R.string.pressure_unit)));
        wetnessValue.setText(Util.getAverage(forecast.getRelwet().getMin(), forecast.getRelwet().getMax())
                .concat(getString(R.string.percent)));
    }
}
