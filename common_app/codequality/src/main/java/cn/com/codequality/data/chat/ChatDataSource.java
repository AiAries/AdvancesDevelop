package cn.com.codequality.data.chat;

import com.bankcomm.framework.network.bean.base.BaseEntity;

import cn.com.codequality.data.chat.bean.Chat;
import io.reactivex.Flowable;

/**
 * Created by A170860 on 2018/6/26.
 */

public interface ChatDataSource {
    Flowable<BaseEntity<Chat>> getChatData();

    Flowable<String> getTestJson();
}
