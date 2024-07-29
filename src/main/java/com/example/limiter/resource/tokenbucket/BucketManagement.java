package com.example.limiter.resource.tokenbucket;

interface BucketManagement {

    void refill(int tokens);
}
