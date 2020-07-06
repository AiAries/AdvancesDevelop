package com.bankcomm.framework.network.api;

import com.bankcomm.framework.network.bean.User;
import com.bankcomm.framework.network.bean.base.BaseEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * Created by  on 2018/6/1.
 * 公用的接口
 */

public interface CommonApi {

    @GET("login/")
    Call<String> login(@Field("username") String username, @Field("password") String password);

    @GET("testJson.json")

    Call<BaseEntity<User>> getUser();
}
