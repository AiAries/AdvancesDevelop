package techown.login.business.main_page;

import android.support.annotation.NonNull;

import com.bankcomm.framework.utils.schedulers.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import techown.login.data.MainRepository;

import static com.bankcomm.framework.utils.Utils.checkNotNull;


/**
 * Created by A170860 on 2018/6/22.
 */

public class MainPresenterImp implements MainContract.Presenter {

    private final MainRepository mMainRepository;
    private final MainContract.View mMainView;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    public MainPresenterImp(@NonNull MainContract.View mainView,
                            @NonNull MainRepository mainRepository,
                            @NonNull SchedulerProvider schedulerProvider
                            ) {
        //非空的校验的处理
        mMainRepository = checkNotNull(mainRepository, "tasksRepository cannot be null");
        mMainView = checkNotNull(mainView, "mainView cannot be null!");
        mSchedulerProvider = checkNotNull(schedulerProvider, "schedulerProvider cannot be null");

        mCompositeDisposable = new CompositeDisposable();
        mMainView.setPresenter(this);//让View获取Presenter的实例

    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
