package com.example.limiter.algorithm.tokenbucket;

import java.util.concurrent.atomic.AtomicInteger;

class Bucket implements BucketTaker, BucketManagement {

    private final int maxCapacity;
    private final AtomicInteger currentCapacity;

    Bucket(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = new AtomicInteger(maxCapacity);
    }

    @Override
    public boolean take() {
        return this.currentCapacity.getAndUpdate(c -> Math.max(c - 1, 0)) > 0;
    }

    @Override
    public void refill(int tokens) {
        this.currentCapacity.updateAndGet(c -> Math.min(c + tokens, this.maxCapacity));
    }
}
