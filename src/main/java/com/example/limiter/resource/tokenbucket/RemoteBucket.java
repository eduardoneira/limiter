package com.example.limiter.resource.tokenbucket;

import com.example.limiter.cache.RedisCounterRepository;
import com.example.limiter.resource.Resource;

public class RemoteBucket implements IBucket {

    private final RedisCounterRepository repository;
    private final int maxCapacity;
    private final String id;

    public RemoteBucket(RedisCounterRepository repository, int maxCapacity, String id) {
        this.repository = repository;
        this.maxCapacity = maxCapacity;
        this.id = id;

        this.repository.create(id, maxCapacity);
    }

    @Override
    public void refill(int tokens) {
        this.repository.incrementUpToMax(this.id, tokens, this.maxCapacity);
    }

    @Override
    public boolean use() {
        return repository.decrementUpToMin(this.id, 0);
    }
}
