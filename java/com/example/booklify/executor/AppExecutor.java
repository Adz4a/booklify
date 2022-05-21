package com.example.booklify.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutor {

    private static AppExecutor instance;

    public static AppExecutor getInstance(){
        if (instance == null){
            new AppExecutor();
        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIo(){
        return mNetworkIO;
    }

}
