package com.example.limiter.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackgroundExecutor implements ScheduledExecutor {

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

    public static class Factory implements ScheduledExecutor.Factory {

        @Override
        public ScheduledExecutor create(long period, TimeUnit unit) {
            return new BackgroundExecutor(period, unit);
        }
    }
}
