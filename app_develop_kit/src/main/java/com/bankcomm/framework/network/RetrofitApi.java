package com.bankcomm.framework.network;


import com.bankcomm.framework.BuildConfig;
import com.bankcomm.framework.network.cookie.ClearableCookieJar;
import com.bankcomm.framework.network.cookie.PersistentCookieJar;
import com.bankcomm.framework.network.cookie.cache.SetCookieCache;
import com.bankcomm.framework.network.cookie.persistence.SharedPrefsCookiePersistor;
import com.bankcomm.framework.network.interceptor.CacheControlInterceptor;
import com.bankcomm.framework.network.listener.ConcurrentPrintingEventListener;
import com.bankcomm.ui.base.BaseApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by A170860 on 2018/6/1.
 * 可以动态的修改BaseURl的路径
 * todo RetrofitApi的配置通过Builder来实现
 */

public class RetrofitApi {
    private RetrofitApi() {

    }

    private static Retrofit retrofit;
    private static final Object Object = new Object();
    /**
     * 缓存机制
     * 在响应请求之后在 data/data/<包名>/cache 下建立一个response 文件夹，保持缓存数据。
     * 这样我们就可以在请求的时候，如果判断到没有网络，自动读取缓存的数据。
     * 同样这也可以实现，在我们没有网络的情况下，重新打开App可以浏览的之前显示过的内容。
     * 也就是：判断网络，有网络，则从网络获取，并保存到缓存中，无网络，则从缓存中获取。
     * https://werb.github.io/2016/07/29/%E4%BD%BF%E7%94%A8Retrofit2+OkHttp3%E5%AE%9E%E7%8E%B0%E7%BC%93%E5%AD%98%E5%A4%84%E7%90%86/
     */
    //这里是设置拦截器，供下面的函数调用，辅助作用。
    private static final Interceptor cacheControlInterceptor = new CacheControlInterceptor();

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
                        .addInterceptor(cacheControlInterceptor)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .eventListenerFactory(ConcurrentPrintingEventListener.FACTORY);

                // Log 拦截器
                if (BuildConfig.DEBUG) {
                    builder = SDKManager.initInterceptor(builder);
                }

                retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                        .client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

            }
            return retrofit;
        }
    }
}
