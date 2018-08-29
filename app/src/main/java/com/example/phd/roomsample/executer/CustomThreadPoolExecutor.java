package com.example.phd.roomsample.executer;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutor {
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.MILLISECONDS;
    private static final int KEEP_ALIVE_TIME = 1000;

    private static ThreadPoolExecutor mThreadPoolExecutor;

    static {
        mThreadPoolExecutor = new ThreadPoolExecutor(
                NUMBER_OF_CORES + 5,   // Initial pool size
                NUMBER_OF_CORES + 8,   // Max pool size
                KEEP_ALIVE_TIME,       // Time idle thread waits before terminating
                KEEP_ALIVE_TIME_UNIT,  // Sets the Time Unit for KEEP_ALIVE_TIME
                new LinkedBlockingDeque<Runnable>()); // Work Queue
    }

    public static ThreadPoolExecutor getInstance() {
        return mThreadPoolExecutor;
    }
}