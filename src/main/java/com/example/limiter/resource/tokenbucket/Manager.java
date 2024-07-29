package com.example.limiter.resource.tokenbucket;

import com.example.limiter.executor.ScheduledExecutor;
import com.example.limiter.resource.Resource;

class Manager implements Resource {

    private final Bucket bucket;
    private final int refillCount;
    private final ScheduledExecutor executor;

    Manager(int bucketCapacity, int refillCount, ScheduledExecutor executor) {
        this.bucket = new Bucket(bucketCapacity);
        this.refillCount = refillCount;
        this.executor = executor;

        this.executor.schedule(this::refill);
    }

    private void refill() {
        this.bucket.refill(this.refillCount);
    }

    ScheduledExecutor getExecutor() {
        return executor;
    }

    @Override
    public boolean use() {
        return this.bucket.take();
    }
}
