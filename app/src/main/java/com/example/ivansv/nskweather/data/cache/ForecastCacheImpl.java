package com.example.ivansv.nskweather.data.cache;

import android.content.Context;

import com.example.ivansv.nskweather.data.cache.serializer.Serializer;
import com.example.ivansv.nskweather.data.exception.ForecastNotFoundException;
import com.example.ivansv.nskweather.data.model.Forecast;
import com.example.ivansv.nskweather.domain.executor.ThreadExecutor;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class ForecastCacheImpl implements ForecastCache {
    private static final String DEFAULT_FILE_NAME = "forecast_";
    private final Context context;
    private final ThreadExecutor threadExecutor;
    private final FileManager fileManager;
    private final File cacheDir;
    private final Serializer serializer;

    @Inject
    ForecastCacheImpl(Context context, FileManager fileManager, ThreadExecutor threadExecutor, Serializer serializer) {
        this.context = context.getApplicationContext();
        this.fileManager = fileManager;
        this.threadExecutor = threadExecutor;
        this.serializer = serializer;
        this.cacheDir = this.context.getCacheDir();
    }

    @Override
    public Observable<List<Forecast>> get() {
        return null;
    }

    @Override
    public Observable<Forecast> get(final String forecastTod) {
        return Observable.create(emitter -> {
            final File forecastsFile = ForecastCacheImpl.this.buildFile(forecastTod);
            final String fileContent = ForecastCacheImpl.this.fileManager.readFileContent(forecastsFile);
            final Forecast forecast = ForecastCacheImpl.this.serializer.deserialize(fileContent, Forecast.class);
            if (forecast != null) {
                emitter.onNext(forecast);
                emitter.onCompleted();
            } else {
                emitter.onError(new ForecastNotFoundException());
            }
        });
    }

    @Override
    public void save(List<Forecast> forecasts) {
        for (Forecast forecast : forecasts) {
            save(forecast);
        }
    }

    @Override
    public void save(Forecast forecast) {
        if (forecast != null) {
            final File forecastFile = this.buildFile(forecast.getTod());
            final String jsonString = this.serializer.serialize(forecast, Forecast.class);
            this.executeAsynchronously(new CacheWriter(this.fileManager, forecastFile, jsonString));
        }
    }

    private File buildFile(String tod) {
        String fileNameBuilder = this.cacheDir.getPath() +
                File.separator +
                DEFAULT_FILE_NAME +
                tod;
        return new File(fileNameBuilder);
    }

    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    @Override
    public boolean isCached() {
        return true;
    }
}
