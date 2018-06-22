package techown.login.business.main_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bankcomm.ui.base.ShadeBaseActivity;

import techown.login.R;

import static android.R.attr.fragment;


public class MainPageActivity extends ShadeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Fragment mainFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (mainFragment==null) {
            mainFragment = new MainFragment();
        }
        mainFragment.setPresent(new MainPresenterImp());
        getSupportFragmentManager().beginTransaction().add(
                mainFragment, ""
        ).commit();
    }
}
