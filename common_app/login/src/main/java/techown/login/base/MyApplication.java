package techown.login.base;


import android.support.multidex.MultiDex;

import com.bankcomm.ui.base.BaseApplication;

/**
 * Created by A170860 on 2018/6/4.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
    }

    private void initConfig() {
        MultiDex.install(this);
    }
}
