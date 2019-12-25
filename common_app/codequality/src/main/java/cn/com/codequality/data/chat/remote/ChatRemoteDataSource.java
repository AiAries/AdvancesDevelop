package cn.com.codequality.data.chat.remote;

import com.bankcomm.framework.network.RetrofitManager;
import com.bankcomm.framework.network.bean.base.BaseEntity;

import cn.com.codequality.data.chat.ChatDataSource;
import cn.com.codequality.data.chat.api.ChatApi;
import cn.com.codequality.data.chat.bean.Chat;
import io.reactivex.Flowable;

/**
 * Created by  on 2018/6/26.
 */

public class ChatRemoteDataSource implements ChatDataSource {
    @Override
    public Flowable<BaseEntity<Chat>> getChatData() {

        return RetrofitManager.newBuilder().build().create(ChatApi.class).getChatData(null);
    }

    @Override
    public Flowable<String> getTestJson() {
        return RetrofitManager.newBuilder().baseUrl("http://127.0.0.1:8080/").build().create
                (ChatApi.class).getData();
    }


}
