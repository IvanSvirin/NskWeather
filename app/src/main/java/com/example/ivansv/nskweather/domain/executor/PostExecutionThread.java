package com.example.ivansv.nskweather.domain.executor;

import rx.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
