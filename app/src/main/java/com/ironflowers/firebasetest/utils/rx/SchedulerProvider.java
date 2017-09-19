package com.ironflowers.firebasetest.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Interface for any class providing {@link Scheduler}s for RXJava threading.
 * <p>
 * Used so that we can override the used threads on unit-tests, when for example thec {@link
 * AndroidSchedulers#mainThread()} is not available. In this application, don't use {@link
 * AndroidSchedulers#mainThread()} or {@link Schedulers#io()} but use an injected SchedulerProvider
 * instead.
 */
public interface SchedulerProvider {

    Scheduler ui();

    /**
     * @return Scheduler intended for CPU intensive work. Number of threads is the same as the
     * number of available processors, to optimize CPU usage.
     */
    Scheduler computation();

    /**
     * @return Scheduler intended for IO-bound work. The implementation is backed by an Executor
     * thread-pool that will grow as needed. This can be used for asynchronously performing blocking
     * IO. Do not perform heave CPU computational work on this scheduler, use {@link #computation()}
     * for that.
     */
    Scheduler io();
}