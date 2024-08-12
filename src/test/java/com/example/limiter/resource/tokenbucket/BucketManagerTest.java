package com.example.limiter.resource.tokenbucket;

import com.example.limiter.executor.ScheduledExecutorMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BucketManagerTest {

    @Test
    void testAutomaticRefill() {
        final ScheduledExecutorMock executor = new ScheduledExecutorMock();
        final BucketManager bucketManager = new BucketManager(1, 1, executor);

        assertEquals(executor, bucketManager.getExecutor());

        assertTrue(bucketManager.use());
        assertFalse(bucketManager.use());

        executor.tick();

        assertTrue(bucketManager.use());
    }

}