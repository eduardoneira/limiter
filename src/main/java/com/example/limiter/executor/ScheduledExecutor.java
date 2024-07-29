package com.example.limiter.executor;

import java.util.concurrent.TimeUnit;

public interface ScheduledExecutor {

    void schedule(Runnable command);

    interface Factory {
        ScheduledExecutor create(long period, TimeUnit unit);
    }
}
