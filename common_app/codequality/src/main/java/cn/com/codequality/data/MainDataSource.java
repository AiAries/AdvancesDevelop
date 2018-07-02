package cn.com.codequality.data;

import cn.com.codequality.network.bean.main.MainTabVo;
import io.reactivex.Flowable;

/**
 * Created by A170860 on 2018/6/26.
 */

public interface MainDataSource {
    Flowable<MainTabVo> getMainTabData();
}
