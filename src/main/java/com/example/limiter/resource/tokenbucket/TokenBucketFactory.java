package com.example.limiter.resource.tokenbucket;

import com.example.limiter.executor.ScheduledExecutor;
import com.example.limiter.resource.Resource;
import com.example.limiter.resource.ResourceFactory;

import java.util.concurrent.TimeUnit;

public class TokenBucketFactory implements ResourceFactory {

    private final int bucketSize;
    private final int refillCount;
    private final long refillTime;
    private final ScheduledExecutor.Factory executorFactory;

    public TokenBucketFactory(int bucketSize,
                              int refillCount,
                              long refillTime,
                              ScheduledExecutor.Factory executorFactory) {
        this.bucketSize = bucketSize;
        this.refillCount = refillCount;
        this.refillTime = refillTime;
        this.executorFactory = executorFactory;
    }

    @Override
    public Resource create() {
        return new Manager(
                this.bucketSize,
                this.refillCount,
                this.executorFactory.create(this.refillTime, TimeUnit.SECONDS));
    }
}
