package cn.com.codequality.base;


import com.bankcomm.framework.BuildConfig;
import com.bankcomm.framework.network.FrameworkConstant;
import com.bankcomm.ui.base.BaseApplication;

/**
 * Created by  on 2018/6/4.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
    }

    private void initConfig() {
//        FrameworkConstant.BASE_URL = ConfigConstant.BASE_URL;
        FrameworkConstant.ENABLE_LOG = BuildConfig.DEBUG;
    }
}
