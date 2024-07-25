package com.example.limiter.algorithm.tokenbucket;

public class Manager {

    private final Bucket bucket;
    private final int refillCount;
    private final ScheduledExecutor executor;

    public Manager(int bucketCapacity, int refillCount, ScheduledExecutor executor) {
        this.bucket = new Bucket(bucketCapacity);
        this.refillCount = refillCount;
        this.executor = executor;

        this.executor.schedule(this::refill);
    }

    private void refill() {
        this.bucket.refill(this.refillCount);
    }

    BucketTaker getBucket() {
        return bucket;
    }

    ScheduledExecutor getExecutor() {
        return executor;
    }
}
