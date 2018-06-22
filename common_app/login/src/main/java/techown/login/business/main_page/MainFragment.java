package techown.login.business.main_page;

import com.bankcomm.ui.base.BaseFragment;

import static com.bankcomm.framework.utils.Utils.checkNull;

/**
 * Created by A170860 on 2018/6/22.
 */

public class MainFragment extends BaseFragment implements MainContract.View {

    private MainContract.Presenter mPresenter;

    @Override
    public void setPresent(MainContract.Presenter presenter) {
        checkNull(presenter.getClass());
        mPresenter = presenter;
    }
}
