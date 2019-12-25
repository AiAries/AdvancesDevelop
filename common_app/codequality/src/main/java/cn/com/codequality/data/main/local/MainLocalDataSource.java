package cn.com.codequality.data.main.local;

import cn.com.codequality.data.main.MainDataSource;
import cn.com.codequality.data.main.bean.MainTabVo;
import io.reactivex.Flowable;

/**
 * Created by  on 2018/6/26.
 */

public class MainLocalDataSource implements MainDataSource {
    @Override
    public Flowable<MainTabVo> getMainTabData() {
        return null;
    }
}
