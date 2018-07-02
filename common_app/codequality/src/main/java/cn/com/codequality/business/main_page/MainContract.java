package cn.com.codequality.business.main_page;

import com.bankcomm.framework.mvp.BasePresenter;
import com.bankcomm.framework.mvp.BaseView;

/**
 * Created by A170860 on 2018/6/22.
 */

public interface MainContract {

    public interface View extends BaseView<Presenter> {
        void setText(String text);
    }

    public interface Presenter extends BasePresenter {
        void getMainData();
    }
}
