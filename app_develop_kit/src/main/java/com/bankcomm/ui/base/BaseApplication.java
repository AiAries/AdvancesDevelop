package com.bankcomm.ui.base;

import android.app.Application;

import androidx.multidex.MultiDex;

/**
 * Created by  on 2018/6/21.
 */

public class BaseApplication extends Application {
    public static BaseApplication mGlobalApp;
    @Override
    public void onCreate() {
        super.onCreate();
        mGlobalApp = this;
        MultiDex.install(this);
    }
}
