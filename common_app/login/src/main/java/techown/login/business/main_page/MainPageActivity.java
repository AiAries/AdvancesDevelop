package techown.login.business.main_page;

import android.os.Bundle;

import com.bankcomm.framework.utils.ActivityUtils;
import com.bankcomm.framework.utils.schedulers.SchedulerProvider;
import com.bankcomm.ui.base.ShadeBaseActivity;

import techown.login.R;
import techown.login.data.MainRepository;
import techown.login.data.local.MainLocalDataSource;
import techown.login.data.remote.MainRemoteDataSource;


public class MainPageActivity extends ShadeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (mainFragment == null) {
            mainFragment = new MainFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),mainFragment,R.id.main_container);
        }
        MainPresenterImp presenterImp = new MainPresenterImp(
                mainFragment,
                MainRepository.getInstance(new MainRemoteDataSource(),new MainLocalDataSource()),
                SchedulerProvider.getInstance()
        );
    }
}
