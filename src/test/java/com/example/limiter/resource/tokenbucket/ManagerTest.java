package com.example.limiter.resource.tokenbucket;

import com.example.limiter.executor.ScheduledExecutorMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    void testAutomaticRefill() {
        final ScheduledExecutorMock executor = new ScheduledExecutorMock();
        final Manager manager = new Manager(1, 1, executor);

        assertEquals(executor, manager.getExecutor());

        assertTrue(manager.use());
        assertFalse(manager.use());

        executor.tick();

        assertTrue(manager.use());
    }

}