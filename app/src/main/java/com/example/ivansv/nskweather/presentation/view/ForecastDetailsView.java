/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.ivansv.nskweather.presentation.view;


import com.example.ivansv.nskweather.data.model.Forecast;

public interface ForecastDetailsView extends LoadDataView {

  void renderForecast(Forecast forecast);
}
