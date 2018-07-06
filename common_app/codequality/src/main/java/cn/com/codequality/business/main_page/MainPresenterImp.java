package cn.com.codequality.business.main_page;

import android.support.annotation.NonNull;

import com.bankcomm.framework.log.AresLog;
import com.bankcomm.framework.utils.schedulers.SchedulerProvider;

import cn.com.codequality.data.MainRepository;
import cn.com.codequality.network.bean.main.MainTabVo;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.bankcomm.framework.utils.Utils.checkNotNull;


/**
 * Created by A170860 on 2018/6/22.
 */

public class MainPresenterImp implements MainContract.Presenter {

    public static final String TAG = MainPresenterImp.class.getSimpleName();
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
        getMainData();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getMainData() {
        mCompositeDisposable.clear();
        Disposable disposable = mMainRepository.getMainTabData().observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Consumer<MainTabVo>() {
                    @Override
                    public void accept(MainTabVo mainTabVo) {
                        mMainView.setText(mainTabVo.getSTATUS());
                        AresLog.d(TAG, "accept: mainTabVo" + mainTabVo.getSTATUS());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        AresLog.d(TAG, "accept: throwable" + throwable);
                        mMainView.setText(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

}
