package com.example.limiter.resource.tokenbucket;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "tokenbucket")
public class TokenBucketConfiguration {

    private final int bucketSize;
    private final int refillCount;
    private final long refillTime;
    private final boolean useRemote;

    @ConstructorBinding
    public TokenBucketConfiguration(int bucketSize, int refillCount, long refillTime, boolean useRemote) {
        this.bucketSize = bucketSize;
        this.refillCount = refillCount;
        this.refillTime = refillTime;
        this.useRemote = useRemote;
    }

    public int getBucketSize() {
        return bucketSize;
    }

    public int getRefillCount() {
        return refillCount;
    }

    public long getRefillTime() {
        return refillTime;
    }

    public boolean isUseRemote() {
        return useRemote;
    }
}
