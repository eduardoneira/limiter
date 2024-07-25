package com.example.limiter.algorithm.tokenbucket;

public class Bucket {

    private final int maxCapacity;
    private int currentCapacity;

    public Bucket(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = maxCapacity;
    }

    public boolean take() {
        if (this.currentCapacity > 0) {
            this.currentCapacity--;
            return true;
        }

        return false;
    }

    public void refill(int i) {
        this.currentCapacity = Math.min(this.currentCapacity + i, this.maxCapacity);
    }
}
