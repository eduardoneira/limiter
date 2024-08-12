package com.example.limiter.resource.tokenbucket;

import com.example.limiter.cache.RedisCounterRepository;
import com.example.limiter.resource.Resource;

public class RemoteBucket implements Resource, ManageableBucket {

    private final RedisCounterRepository repository;
    private final String id;

    public RemoteBucket(RedisCounterRepository repository, String id) {
        this.repository = repository;
        this.id = id;
    }

    @Override
    public void refill(int tokens) {
        this.repository.increment(this.id, tokens);
    }

    @Override
    public boolean use() {
        repository.decrement(this.id);
        return true;
    }
}
