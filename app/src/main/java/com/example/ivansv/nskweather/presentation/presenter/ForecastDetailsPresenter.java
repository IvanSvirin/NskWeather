/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.ivansv.nskweather.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.ivansv.nskweather.data.model.Forecast;
import com.example.ivansv.nskweather.domain.exception.DefaultErrorBundle;
import com.example.ivansv.nskweather.domain.exception.ErrorBundle;
import com.example.ivansv.nskweather.domain.interactor.DefaultSubscriber;
import com.example.ivansv.nskweather.domain.interactor.UseCase;
import com.example.ivansv.nskweather.presentation.exception.ErrorMessageFactory;
import com.example.ivansv.nskweather.presentation.internal.di.PerActivity;
import com.example.ivansv.nskweather.presentation.view.ForecastDetailsView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class ForecastDetailsPresenter implements Presenter {
    private ForecastDetailsView forecastDetailsView;
    private final UseCase getForecastDetailsUseCase;

    @Inject
    ForecastDetailsPresenter(@Named("forecastDetails") UseCase getForecastDetailsUseCase) {
        this.getForecastDetailsUseCase = getForecastDetailsUseCase;
    }

    public void setView(@NonNull ForecastDetailsView forecastDetailsView) {
        this.forecastDetailsView = forecastDetailsView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getForecastDetailsUseCase.unsubscribe();
        this.forecastDetailsView = null;
    }

    public void initialize() {
        this.loadForecastDetails();
    }

    private void loadForecastDetails() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getForecastDetails();
    }

    private void showViewLoading() {
        this.forecastDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.forecastDetailsView.hideLoading();
    }

    private void showViewRetry() {
        this.forecastDetailsView.showRetry();
    }

    private void hideViewRetry() {
        this.forecastDetailsView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.forecastDetailsView.context(), errorBundle.getException());
        this.forecastDetailsView.showError(errorMessage);
    }

    private void showForecastDetailsInView(Forecast forecast) {
        this.forecastDetailsView.renderForecast(forecast);
    }

    private void getForecastDetails() {
        this.getForecastDetailsUseCase.execute(new ForecastDetailsSubscriber());
    }

    private final class ForecastDetailsSubscriber extends DefaultSubscriber<Forecast> {

        @Override
        public void onCompleted() {
            ForecastDetailsPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            ForecastDetailsPresenter.this.hideViewLoading();
            ForecastDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            ForecastDetailsPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(Forecast forecast) {
            ForecastDetailsPresenter.this.showForecastDetailsInView(forecast);
        }
    }
}
