package com.example.limiter.algorithm.tokenbucket;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class BackgroundExecutorTest {

    @Test
    void testSchedule() throws InterruptedException {
        final BackgroundExecutor backgroundExecutor = new BackgroundExecutor(1, TimeUnit.SECONDS);
        final CountDownLatch latch = new CountDownLatch(1);

        backgroundExecutor.schedule(latch::countDown);

        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }

}