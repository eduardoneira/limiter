package com.example.limiter.algorithm.tokenbucket;

interface ScheduledExecutor {

    void schedule(Runnable command);
}
