package com.example.limiter.resource.tokenbucket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BucketTest {

    @Test
    void testCapacity() {
        final int capacity = 4;
        final Bucket bucket = new Bucket(capacity);

        for (int i = 0; i < capacity; i++) {
            assertTrue(bucket.use());
        }

        assertFalse(bucket.use());
    }

    @Test
    void testRefill() {
        final Bucket bucket = new Bucket(1);

        assertTrue(bucket.use());
        assertFalse(bucket.use());

        bucket.refill(1);

        assertTrue(bucket.use());
        assertFalse(bucket.use());
    }

    @Test
    void testRefillOverflow() {
        final Bucket bucket = new Bucket(1);

        bucket.refill(5);

        assertTrue(bucket.use());
        assertFalse(bucket.use());
    }
}