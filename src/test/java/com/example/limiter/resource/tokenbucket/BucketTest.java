package com.example.limiter.resource.tokenbucket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BucketTest {

    @Test
    void testCapacity() {
        final int capacity = 4;
        final Bucket bucket = new Bucket(capacity);

        for (int i = 0; i < capacity; i++) {
            assertTrue(bucket.take());
        }

        assertFalse(bucket.take());
    }

    @Test
    void testRefill() {
        final Bucket bucket = new Bucket(1);

        assertTrue(bucket.take());
        assertFalse(bucket.take());

        bucket.refill(1);

        assertTrue(bucket.take());
        assertFalse(bucket.take());
    }

    @Test
    void testRefillOverflow() {
        final Bucket bucket = new Bucket(1);

        bucket.refill(5);

        assertTrue(bucket.take());
        assertFalse(bucket.take());
    }
}