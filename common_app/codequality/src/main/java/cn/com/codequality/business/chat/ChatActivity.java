package cn.com.codequality.business.chat;

import android.os.Bundle;

import com.bankcomm.framework.utils.ActivityUtils;
import com.bankcomm.framework.utils.schedulers.SchedulerProvider;
import com.bankcomm.ui.base.BaseActivity;

import cn.com.codequality.R;
import cn.com.codequality.data.chat.ChatRepository;
import cn.com.codequality.data.chat.local.ChatLocalDataSource;
import cn.com.codequality.data.chat.remote.ChatRemoteDataSource;

public class ChatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ChatFragment chatFragment = (ChatFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (chatFragment == null) {
            chatFragment = new ChatFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),chatFragment,R.id.main_container);
        }
        new ChatPresenterImp(
                chatFragment,
                ChatRepository.getInstance(new ChatRemoteDataSource(),new ChatLocalDataSource()),
                SchedulerProvider.getInstance()
        );
    }
}
