package com.example.limiter.resource.tokenbucket;

import com.example.limiter.executor.ScheduledExecutorMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BucketManagerTest {

    @Test
    void testAutomaticRefill() {
        final ScheduledExecutorMock executor = new ScheduledExecutorMock();
        final Bucket bucket = new Bucket(1);
        final BucketManager bucketManager = new BucketManager(1, executor);

        assertTrue(bucket.use());
        assertFalse(bucket.use());

        executor.tick();
        assertFalse(bucket.use());

        bucketManager.manage(bucket);
        executor.tick();

        assertTrue(bucket.use());
    }

}