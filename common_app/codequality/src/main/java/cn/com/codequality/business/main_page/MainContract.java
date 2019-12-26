package cn.com.codequality.business.main_page;

import com.bankcomm.framework.mvp.BasePresenter;
import com.bankcomm.framework.mvp.BaseView;

/**
 * Created by  on 2018/6/22.
 */

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void setText(String text);
    }

    interface Presenter extends BasePresenter {
        void getMainData();
    }
}
