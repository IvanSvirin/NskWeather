package com.example.ivansv.nskweather.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.ivansv.nskweather.data.model.Forecast;
import com.example.ivansv.nskweather.domain.exception.DefaultErrorBundle;
import com.example.ivansv.nskweather.domain.exception.ErrorBundle;
import com.example.ivansv.nskweather.domain.interactor.DefaultSubscriber;
import com.example.ivansv.nskweather.domain.interactor.UseCase;
import com.example.ivansv.nskweather.presentation.exception.ErrorMessageFactory;
import com.example.ivansv.nskweather.presentation.internal.di.PerActivity;
import com.example.ivansv.nskweather.presentation.view.ForecastListView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;


@PerActivity
public class ForecastListPresenter implements Presenter {
    private ForecastListView forecastListView;
    private final UseCase getForecastListUseCase;

    @Inject
    ForecastListPresenter(@Named("forecastList") UseCase getForecastListUseCase) {
        this.getForecastListUseCase = getForecastListUseCase;
    }

    public void setView(@NonNull ForecastListView forecastListView) {
        this.forecastListView = forecastListView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getForecastListUseCase.unsubscribe();
        this.forecastListView = null;
    }

    public void initialize() {
        this.loadForecastList();
    }

    private void loadForecastList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getForecastList();
    }

    private void showViewLoading() {
        this.forecastListView.showLoading();
    }

    private void hideViewLoading() {
        this.forecastListView.hideLoading();
    }

    private void showViewRetry() {
        this.forecastListView.showRetry();
    }

    private void hideViewRetry() {
        this.forecastListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.forecastListView.context(), errorBundle.getException());
        this.forecastListView.showError(errorMessage);
    }

    private void showForecastCollectionInView(List<Forecast> forecasts) {
        this.forecastListView.renderForecastList(forecasts);
    }

    private void getForecastList() {
        this.getForecastListUseCase.execute(new ForecastListSubscriber());
    }

    private final class ForecastListSubscriber extends DefaultSubscriber<List<Forecast>> {

        @Override
        public void onCompleted() {
            ForecastListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            ForecastListPresenter.this.hideViewLoading();
            ForecastListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            ForecastListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<Forecast> forecasts) {
            ForecastListPresenter.this.showForecastCollectionInView(forecasts);
        }
    }
}
