package com.example.limiter.resource.tokenbucket;

import com.example.limiter.executor.ScheduledExecutorMock;
import com.example.limiter.resource.Resource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenBucketFactoryTest {

    @Test
    void testCreate() {
        final ScheduledExecutorMock executor = new ScheduledExecutorMock();
        final TokenBucketFactory factory = new TokenBucketFactory(
                new TokenBucketConfiguration(1, 1, 1),
                (_, _) -> executor);

        final Resource resource = factory.create("test");

        assertTrue(resource.use());
        assertFalse(resource.use());

        executor.tick();

        assertTrue(resource.use());
    }
}