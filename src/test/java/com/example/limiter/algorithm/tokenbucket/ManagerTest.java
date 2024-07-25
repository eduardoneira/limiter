package com.example.limiter.algorithm.tokenbucket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    void testAutomaticRefill() {
        final ScheduledExecutorMock executor = new ScheduledExecutorMock();
        final Manager manager = new Manager(1, 1, executor);

        assertEquals(executor, manager.getExecutor());

        final BucketTaker bucket = manager.getBucket();

        assertTrue(bucket.take());
        assertFalse(bucket.take());

        executor.tick();

        assertTrue(bucket.take());
    }

}