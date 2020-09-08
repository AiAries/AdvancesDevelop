package cn.com.codequality.business.chat;

import com.bankcomm.framework.mvp.BasePresenter;
import com.bankcomm.framework.mvp.BaseView;

import java.util.List;

import cn.com.codequality.data.chat.bean.Chat;

/**
 * Created by  on 2018/6/22.
 */

public interface ChatContract {

    interface View extends BaseView<Presenter> {
        void showChatList(List<Chat> data);
        void showNoData();
        void showTestJson(String text);

        void showWaitDialog();

        void dismissDialog();
    }

    interface Presenter extends BasePresenter {
        void getChatDatas();

        void getTestJson();
    }
}
