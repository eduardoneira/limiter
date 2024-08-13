package com.example.limiter.resource.tokenbucket;

interface ManagedBucket {

    void refill(int tokens);
}
