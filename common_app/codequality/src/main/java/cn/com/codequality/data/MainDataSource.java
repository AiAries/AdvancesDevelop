package cn.com.codequality.data;

import io.reactivex.Flowable;
import techown.login.network.bean.main.MainTabVo;

/**
 * Created by A170860 on 2018/6/26.
 */

public interface MainDataSource {
    Flowable<MainTabVo> getMainTabData();
}
