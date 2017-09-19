package com.ironflowers.firebasetest.utils.rx;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Provides {@link Scheduler}s for RXJava threading.
 * <p>
 * More info in {@link SchedulerProvider}
 */
@Singleton
public final class AppSchedulerProvider implements SchedulerProvider {

    @Inject
    public AppSchedulerProvider() {
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }
}