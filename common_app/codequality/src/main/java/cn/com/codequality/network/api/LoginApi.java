package cn.com.codequality.network.api;

import com.bankcomm.framework.network.bean.base.BaseEntity;

import io.reactivex.Observable;
import techown.login.network.bean.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * Created by A170860 on 2018/6/1.
 */


public interface LoginApi {

    @GET("login/")
    Call<String> login(@Field("username") String username, @Field("password") String password);

    @GET("testJson.json")
    Observable<BaseEntity<User>> getUser();
    @GET("testJson.json")
    Call<BaseEntity<User>> getUserByCall();
}
