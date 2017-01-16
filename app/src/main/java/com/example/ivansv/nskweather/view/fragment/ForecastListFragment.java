package com.example.ivansv.nskweather.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivansv.nskweather.R;
import com.example.ivansv.nskweather.model.Forecast;
import com.example.ivansv.nskweather.rest.DataProvider;
import com.example.ivansv.nskweather.view.adapter.ForecastListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;

public class ForecastListFragment extends Fragment {
    private ForecastListAdapter forecastListAdapter;
    private Subscription subscription;
    @BindView(R.id.rv_forecasts)
    RecyclerView forecastList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forecastListAdapter = new ForecastListAdapter(getContext(), new ArrayList<>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_forecast_list, container, false);
        ButterKnife.bind(this, fragmentView);
        forecastList.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastList.setAdapter(forecastListAdapter);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
    }

    public void getData() {
        subscription = DataProvider.getForecastsFromApiObservable()
                .subscribe(new Subscriber<List<Forecast>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Forecast> forecastList) {
                        forecastListAdapter.updateData(forecastList);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @OnClick(R.id.btn_update)
    void updateForecastList() {
        getData();
    }
}
