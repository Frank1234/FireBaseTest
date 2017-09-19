package com.ironflowers.firebasetest.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

/**
 * Provides test {@link Scheduler}s for RXJava threading.
 * <p>
 * More info in {@link SchedulerProvider}
 */
public class TestSchedulerProvider implements SchedulerProvider {

    private final TestScheduler testScheduler;

    public TestSchedulerProvider(final TestScheduler testScheduler) {
        this.testScheduler = testScheduler;
    }

    @Override
    public Scheduler ui() {
        return testScheduler;
    }

    @Override
    public Scheduler computation() {
        return testScheduler;
    }

    @Override
    public Scheduler io() {
        return testScheduler;
    }
}