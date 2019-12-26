package cn.com.codequality.data.chat.local;

import com.bankcomm.framework.network.bean.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

import cn.com.codequality.data.chat.ChatDataSource;
import cn.com.codequality.data.chat.bean.Chat;
import io.reactivex.Flowable;

/**
 * Created by  on 2018/6/26.
 */

public class ChatLocalDataSource implements ChatDataSource {

    @Override
    public Flowable<BaseEntity<Chat>> getChatData() {
        BaseEntity<Chat> data = new BaseEntity<>();
        data.setResponseCode("1");
        List<Chat> d = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Chat e = new Chat();
            e.setId(i+"");
            e.setMessage("message"+ i);
            d.add(e);
        }
        data.setData(d);
        return Flowable.fromArray(data);
    }

    @Override
    public Flowable<String> getTestJson() {
        return null;
    }
}
