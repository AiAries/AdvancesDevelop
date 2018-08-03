package cn.com.codequality.business.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bankcomm.framework.utils.ActivityUtils;
import com.bankcomm.framework.utils.schedulers.SchedulerProvider;

import cn.com.codequality.R;
import cn.com.codequality.data.chat.ChatRepository;
import cn.com.codequality.data.chat.local.ChatLocalDataSource;
import cn.com.codequality.data.chat.remote.ChatRemoteDataSource;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ChatFragment mainFragment = (ChatFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (mainFragment == null) {
            mainFragment = new ChatFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),mainFragment,R.id.main_container);
        }
        new ChatPresenterImp(
                mainFragment,
                ChatRepository.getInstance(new ChatRemoteDataSource(),new ChatLocalDataSource()),
                SchedulerProvider.getInstance()
        );
    }
}
