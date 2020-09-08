package cn.com.codequality.data.chat.api;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.bankcomm.framework.network.bean.base.BaseEntity;

import java.util.Map;

import cn.com.codequality.data.chat.bean.Chat;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by  on 2018/6/22.
 */

public interface ChatApi {

    @GET("common/visualmenumodule.do")
    Flowable<BaseEntity<Chat>> getChatData(@QueryMap Map<String, Object> options);
    @GET("testJson.json")
    Flowable<String> getData();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public  static void get(){
        System.out.println(a);
    }
    int a = 1 ;
}
