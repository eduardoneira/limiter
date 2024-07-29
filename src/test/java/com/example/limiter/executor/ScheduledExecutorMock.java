package com.example.limiter.executor;

import java.util.ArrayList;
import java.util.List;

public class ScheduledExecutorMock implements ScheduledExecutor {

    private final List<Runnable> runnables;

    public ScheduledExecutorMock() {
        this.runnables = new ArrayList<>();
    }

    @Override
    public void schedule(Runnable command) {
        this.runnables.add(command);
    }

    public void tick() {
        for (Runnable runnable : this.runnables) {
            runnable.run();
        }
    }
}
