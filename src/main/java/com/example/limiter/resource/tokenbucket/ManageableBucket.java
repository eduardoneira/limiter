package com.example.limiter.resource.tokenbucket;

interface ManageableBucket {

    void refill(int tokens);
}
