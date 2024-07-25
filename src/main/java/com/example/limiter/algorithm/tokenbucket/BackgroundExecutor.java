package com.example.limiter.algorithm.tokenbucket;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class BackgroundExecutor implements ScheduledExecutor {

    private final ScheduledExecutorService executor;
    private final long period;
    private final TimeUnit unit;

    public BackgroundExecutor(long period, TimeUnit unit) {
        this.period = period;
        this.unit = unit;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }
    
    public void schedule(Runnable runnable) {
        this.executor.scheduleAtFixedRate(runnable, this.period, this.period, this.unit);
    }
}
