package com.bankcomm.framework.network;


import com.bankcomm.framework.BuildConfig;
import com.bankcomm.framework.network.listener.ConcurrentPrintingEventListener;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.internal.Util.checkDuration;

/**
 * Created by A170860 on 2018/6/1.
 * 可以动态的修改BaseURl的路径
 * todo RetrofitApi的配置通过Builder来实现
 */

public final class RetrofitManager {
    private RetrofitManager() {

    }

    private static RetrofitManager.Builder retrofitManagerBuilder;
    private static final Object Object = new Object();

    public static Retrofit getRetrofit() {
        synchronized (Object) {
            if (retrofitManagerBuilder == null) {
                retrofitManagerBuilder = new Builder();
            }
            return retrofitManagerBuilder.build();
        }
    }

    public static RetrofitManager.Builder newBuilder() {
        synchronized (Object) {
            if (retrofitManagerBuilder == null) {
                retrofitManagerBuilder = new Builder();
            }
            return retrofitManagerBuilder;
        }
    }

    public static final class Builder {
        private Retrofit.Builder retrofitBuilder;
        private OkHttpClient.Builder okHttpBuilder;

        public Builder() {

            // 指定缓存路径,缓存大小 50Mb
//            Cache cache = new Cache(new File(BaseApplication.mGlobalApp.getCacheDir(), "HttpCache"), 1024 * 1024 * 50);

            // Cookie 持久化
//            ClearableCookieJar cookieJar =
//                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.mGlobalApp));
            okHttpBuilder = new OkHttpClient.Builder();
            okHttpBuilder/*.cookieJar(cookieJar)*/
//                    .cache(cache)
//                        .sslSocketFactory(new CustomSSLSocketFactory(),new CustomX509TrustManager())
//                        .authenticator(new ClientAuthenticator())
//                    .addInterceptor(new CacheControlInterceptor())
//                    .addInterceptor(new GzipRequestInterceptor())
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .eventListenerFactory(ConcurrentPrintingEventListener.FACTORY);
            //LOG拦截器的配置
            if (BuildConfig.DEBUG) {
                okHttpBuilder = SDKManager.initInterceptor(okHttpBuilder);
            }

            retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder.baseUrl(FrameworkConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        }

        public Builder readTimeout(int timeout) {
            int readTimeout = checkDuration("timeout", timeout, TimeUnit.SECONDS);
            okHttpBuilder.readTimeout(readTimeout, TimeUnit.SECONDS);
            return this;
        }

        public Builder writeTimeout(int timeout) {
            int writeTimeout = checkDuration("timeout", timeout, TimeUnit.SECONDS);
            okHttpBuilder.readTimeout(writeTimeout, TimeUnit.SECONDS);
            return this;
        }

        public Builder connectTimeout(int timeout) {
            int connectTimeout = checkDuration("timeout", timeout, TimeUnit.SECONDS);
            okHttpBuilder.readTimeout(connectTimeout, TimeUnit.SECONDS);
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            retrofitBuilder.baseUrl(baseUrl);
            return this;
        }

        public Retrofit build() {
            retrofitBuilder.client(okHttpBuilder.build());
            return retrofitBuilder.build();
        }
    }

}
