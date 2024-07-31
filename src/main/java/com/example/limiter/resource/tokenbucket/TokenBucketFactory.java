package com.example.limiter.resource.tokenbucket;

import com.example.limiter.executor.ScheduledExecutor;
import com.example.limiter.resource.Resource;
import com.example.limiter.resource.ResourceFactory;

import java.util.concurrent.TimeUnit;

public class TokenBucketFactory implements ResourceFactory {

    private final TokenBucketConfiguration configuration;
    private final ScheduledExecutor.Factory executorFactory;

    public TokenBucketFactory(TokenBucketConfiguration configuration,
                              ScheduledExecutor.Factory executorFactory) {
        this.configuration = configuration;
        this.executorFactory = executorFactory;
    }

    @Override
    public Resource create() {
        return new Manager(
                this.configuration.getBucketSize(),
                this.configuration.getRefillCount(),
                this.executorFactory.create(this.configuration.getRefillTime(), TimeUnit.SECONDS));
    }
}
