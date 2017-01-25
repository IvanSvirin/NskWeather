package com.example.ivansv.nskweather.presentation.view;

import android.content.Context;

interface LoadDataView {

    void hideLoading();
    void showLoading();

    void showRetry();
    void hideRetry();

    void showError(String message);
    Context context();
}
