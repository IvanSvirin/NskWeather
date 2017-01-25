package com.example.ivansv.nskweather.presentation.internal.di.module;

import com.example.ivansv.nskweather.data.net.WeatherService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Module
public class NetModule {
    private static final String API_BASE_URL = "http://informer.gismeteo.ru/xml/";

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        int connectTimeoutInSeconds = 30;
        int writeTimeoutInSeconds = 30;
        int readTimeoutInSeconds = 30;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(connectTimeoutInSeconds, TimeUnit.SECONDS)
                .readTimeout(readTimeoutInSeconds, TimeUnit.SECONDS)
                .writeTimeout(writeTimeoutInSeconds, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(API_BASE_URL)
                .client(httpClient.build())
                .build();
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService(Retrofit retrofit){
        return retrofit.create(WeatherService.class);
    }
}
