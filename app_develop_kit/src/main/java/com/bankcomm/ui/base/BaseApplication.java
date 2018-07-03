package com.bankcomm.ui.base;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * Created by A170860 on 2018/6/21.
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
