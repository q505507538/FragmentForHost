package com.jay.android.fragmentforhost;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

public class MyApplication extends Application {
    private static MyApplication mContext = null;
    private static Handler mMainThreadHandler = null;
    private static Looper mMainThreadLooper = null;
    private static Thread mMainThead = null;
    private static int mMainTheadId;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        this.mContext = this;
        this.mMainThreadHandler = new Handler();
        this.mMainThreadLooper = getMainLooper();
        this.mMainThead = Thread.currentThread();
        this.mMainTheadId = android.os.Process.myTid();
    }

    public static MyApplication getApplication() {
        return mContext;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

    public static Thread getMainThread() {
        return mMainThead;
    }

    public static int getMainThreadId() {
        return mMainTheadId;
    }

}