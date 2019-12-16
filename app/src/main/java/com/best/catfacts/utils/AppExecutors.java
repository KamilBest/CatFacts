package com.best.catfacts.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {
    private static AppExecutors instance;

    private static final int NUMBER_OF_THREADS = 3;
    private final ScheduledExecutorService getNetworkIO = Executors.newScheduledThreadPool(NUMBER_OF_THREADS);

    public static AppExecutors getInstance() {
        if (instance == null)
            instance = new AppExecutors();
        return instance;
    }

    public ScheduledExecutorService networkIO() {
        return getNetworkIO;
    }

}
