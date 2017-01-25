package com.example.ivansv.nskweather.presentation.view;

import com.example.ivansv.nskweather.data.model.Forecast;

import java.util.List;


public interface ForecastListView extends LoadDataView {

    void renderForecastList(List<Forecast> forecastList);
}
