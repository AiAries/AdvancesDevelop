package cn.com.codequality.data.main.api;

import java.util.Map;

import cn.com.codequality.data.main.bean.MainTabVo;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by A170860 on 2018/6/22.
 */

public interface MainApi {

    @GET("common/visualmenumodule.do")
    Flowable<MainTabVo> getMainData(@QueryMap Map<String, Object> options);
}
