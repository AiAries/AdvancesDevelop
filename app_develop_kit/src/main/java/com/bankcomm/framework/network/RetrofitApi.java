package com.bankcomm.framework.network;


import com.bankcomm.framework.BuildConfig;
import com.bankcomm.framework.network.cookie.ClearableCookieJar;
import com.bankcomm.framework.network.cookie.PersistentCookieJar;
import com.bankcomm.framework.network.cookie.cache.SetCookieCache;
import com.bankcomm.framework.network.cookie.persistence.SharedPrefsCookiePersistor;
import com.bankcomm.framework.network.interceptor.CacheControlInterceptor;
import com.bankcomm.framework.network.interceptor.GzipRequestInterceptor;
import com.bankcomm.framework.network.listener.ConcurrentPrintingEventListener;
import com.bankcomm.ui.base.BaseApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by A170860 on 2018/6/1.
 * 可以动态的修改BaseURl的路径
 * todo RetrofitApi的配置通过Builder来实现
 */

public final class RetrofitApi {
    private RetrofitApi() {

    }

    private static Retrofit retrofit;
    private static final Object Object = new Object();

    public static Retrofit getRetrofit() {
        synchronized (Object) {
            if (retrofit == null) {
                // 指定缓存路径,缓存大小 50Mb
                Cache cache = new Cache(new File(BaseApplication.mGlobalApp.getCacheDir(), "HttpCache"), 1024 * 1024 * 50);

                // Cookie 持久化
                ClearableCookieJar cookieJar =
                        new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.mGlobalApp));

                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .cookieJar(cookieJar)
                        .cache(cache)
                        .sslSocketFactory(new CustomSSLSocketFactory(),new CustomX509TrustManager())
                        .authenticator(new ClientAuthenticator())
                        .addInterceptor(new CacheControlInterceptor())
                        .addInterceptor(new GzipRequestInterceptor())
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .eventListenerFactory(ConcurrentPrintingEventListener.FACTORY);

                //LOG拦截器的配置
                if (BuildConfig.DEBUG) {
                    builder = SDKManager.initInterceptor(builder);
                }

                retrofit = new Retrofit.Builder().baseUrl(FrameworkConstant.BASE_URL)
                        .client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

            }
            return retrofit;
        }
    }
}
