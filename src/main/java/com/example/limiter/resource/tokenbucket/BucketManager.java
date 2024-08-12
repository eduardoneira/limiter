package com.example.limiter.resource.tokenbucket;

import com.example.limiter.executor.ScheduledExecutor;

class BucketManager {

    private final int refillCount;
    private final ScheduledExecutor executor;

    BucketManager(int refillCount, ScheduledExecutor executor) {
        this.refillCount = refillCount;
        this.executor = executor;
    }

    void manage(ManageableBucket bucket) {
        this.executor.schedule(() -> bucket.refill(this.refillCount));
    }
}
