package techown.login.business.main_page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankcomm.ui.base.BaseFragment;

import static com.bankcomm.framework.utils.Utils.checkNotNull;

/**
 * Created by A170860 on 2018/6/22.
 */

public class MainFragment extends BaseFragment implements MainContract.View {

    private MainContract.Presenter mPresenter;

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        checkNotNull(presenter,"mainPresenter cannot be null");
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
