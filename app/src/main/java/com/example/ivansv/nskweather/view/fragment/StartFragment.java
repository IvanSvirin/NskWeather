package com.example.ivansv.nskweather.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivansv.nskweather.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_start, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @OnClick(R.id.btn_update_start)
    void showForecastList() {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ForecastListFragment())
                .addToBackStack(null)
                .commit();
    }
}