package com.example.limiter.resource.tokenbucket;

import com.example.limiter.executor.ScheduledExecutor;
import com.example.limiter.resource.Resource;
import com.example.limiter.resource.ResourceFactory;

import java.util.concurrent.TimeUnit;

public class TokenBucketFactory implements ResourceFactory {

    private final TokenBucketConfiguration configuration;
    private final BucketManager bucketManager;

    public TokenBucketFactory(TokenBucketConfiguration configuration,
                              ScheduledExecutor.Factory executorFactory) {
        this.configuration = configuration;
        this.bucketManager = new BucketManager(
                this.configuration.getRefillCount(),
                executorFactory.create(this.configuration.getRefillTime(), TimeUnit.SECONDS));
    }

    @Override
    public Resource create(String id) {
        final Bucket bucket = new Bucket(this.configuration.getBucketSize());

        this.bucketManager.manage(bucket);

        return bucket;
    }
}
