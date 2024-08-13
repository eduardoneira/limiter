package com.example.limiter.resource.tokenbucket;

import com.example.limiter.cache.RedisCounterRepository;
import com.example.limiter.executor.ScheduledExecutor;
import com.example.limiter.resource.Resource;
import com.example.limiter.resource.ResourceFactory;

import java.util.concurrent.TimeUnit;

public class TokenBucketFactory implements ResourceFactory {

    private final TokenBucketConfiguration configuration;
    private final BucketManager bucketManager;
    private final RedisCounterRepository redisCounterRepository;

    public TokenBucketFactory(TokenBucketConfiguration configuration,
                              ScheduledExecutor.Factory executorFactory,
                              RedisCounterRepository redisCounterRepository) {
        this.configuration = configuration;
        this.bucketManager = new BucketManager(
                this.configuration.getRefillCount(),
                executorFactory.create(this.configuration.getRefillTime(), TimeUnit.SECONDS));
        this.redisCounterRepository = redisCounterRepository;
    }

    @Override
    public Resource create(String id) {
        final IBucket bucket = createBucket(id);
        this.bucketManager.manage(bucket);
        return bucket;
    }

    private IBucket createBucket(String id) {
        if (this.configuration.isUseRemote()) {
            return new RemoteBucket(this.redisCounterRepository, this.configuration.getBucketSize(), id);
        } else {
            return new Bucket(this.configuration.getBucketSize());
        }
    }
}
