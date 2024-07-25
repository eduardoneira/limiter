package com.example.limiter.algorithm.tokenbucket;

class ScheduledExecutorMock implements ScheduledExecutor {

    private Runnable runnable;

    ScheduledExecutorMock() {
        this.runnable = null;
    }

    @Override
    public void schedule(Runnable command) {
        this.runnable = command;
    }

    void tick() {
        if (this.runnable != null)
            this.runnable.run();
    }
}
