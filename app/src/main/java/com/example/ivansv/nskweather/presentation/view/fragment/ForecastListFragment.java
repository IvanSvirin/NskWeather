package com.example.ivansv.nskweather.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ivansv.nskweather.R;
import com.example.ivansv.nskweather.data.model.Forecast;
import com.example.ivansv.nskweather.presentation.internal.di.component.ForecastComponent;
import com.example.ivansv.nskweather.presentation.presenter.ForecastListPresenter;
import com.example.ivansv.nskweather.presentation.view.ForecastListView;
import com.example.ivansv.nskweather.presentation.view.adapter.ForecastListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForecastListFragment extends BaseFragment implements ForecastListView {
    @Inject
    ForecastListPresenter forecastListPresenter;
    @Inject
    ForecastListAdapter forecastListAdapter;
    @Bind(R.id.rv_forecasts)
    RecyclerView forecastList;
    @Bind(R.id.btn_update)
    Button btnUpdate;

    public ForecastListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(ForecastComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_forecast_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.forecastListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadForecastList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.forecastListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.forecastListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        forecastList.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.forecastListPresenter.destroy();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showRetry() {
    }

    @Override
    public void hideRetry() {
    }

    public void renderForecastList(List<Forecast> forecastList) {
        if (forecastList != null) {
            this.forecastListAdapter.updateData(forecastList);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        this.forecastList.setLayoutManager(new LinearLayoutManager(context()));
        this.forecastList.setAdapter(forecastListAdapter);
    }

    private void loadForecastList() {
        this.forecastListPresenter.initialize();
    }

    @OnClick(R.id.btn_update)
    void updateForecastList() {
        loadForecastList();
    }
}
