package cn.com.codequality.data.local;

import cn.com.codequality.data.MainDataSource;
import cn.com.codequality.network.bean.main.MainTabVo;
import io.reactivex.Flowable;

/**
 * Created by A170860 on 2018/6/26.
 */

public class MainLocalDataSource implements MainDataSource {
    @Override
    public Flowable<MainTabVo> getMainTabData() {
        return null;
    }
}
