package cn.com.codequality.business.main_page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bankcomm.framework.utils.ActivityUtils;
import com.bankcomm.framework.utils.schedulers.SchedulerProvider;

import cn.com.codequality.R;
import cn.com.codequality.data.main.MainRepository;
import cn.com.codequality.data.main.local.MainLocalDataSource;
import cn.com.codequality.data.main.remote.MainRemoteDataSource;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (mainFragment == null) {
            mainFragment = new MainFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),mainFragment,R.id.main_container);
        }
        new MainPresenterImp(
                mainFragment,
                MainRepository.getInstance(new MainRemoteDataSource(),new MainLocalDataSource()),
                SchedulerProvider.getInstance()
        );
    }
}
