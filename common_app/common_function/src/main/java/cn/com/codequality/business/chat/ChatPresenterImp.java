package cn.com.codequality.business.chat;

import android.support.annotation.NonNull;

import com.bankcomm.framework.network.bean.base.BaseEntity;
import com.bankcomm.framework.utils.schedulers.SchedulerProvider;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import cn.com.codequality.business.chat.ChatContract.Presenter;
import cn.com.codequality.data.chat.ChatRepository;
import cn.com.codequality.data.chat.bean.Chat;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.bankcomm.framework.utils.Utils.checkNotNull;

public class ChatPresenterImp implements Presenter {

    public static final String TAG = ChatPresenterImp.class.getSimpleName();
    private final ChatRepository mChatRepository;
    private final ChatContract.View mChatView;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    public ChatPresenterImp(@NonNull ChatContract.View chatView,
                            @NonNull ChatRepository chatRepository,
                            @NonNull SchedulerProvider schedulerProvider
    ) {
        //非空的校验的处理
        mChatRepository = checkNotNull(chatRepository, "tasksRepository cannot be null");
        mChatView = checkNotNull(chatView, "chatView cannot be null!");
        mSchedulerProvider = checkNotNull(schedulerProvider, "schedulerProvider cannot be null");

        mCompositeDisposable = new CompositeDisposable();
        mChatView.setPresenter(this);//让View获取Presenter的实例
    }


    @Override
    public void getChatDatas() {
        Disposable disposable = mChatRepository.getChatData().observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Consumer<BaseEntity<Chat>>() {
                    @Override
                    public void accept(BaseEntity<Chat> listBaseEntity) {
                        List<Chat> data = listBaseEntity.getData();
                        if (data == null || data.size() == 0) {
                            mChatView.showNoData();
                        } else {
                            mChatView.showChatList(data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        mChatView.showNoData();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getTestJson() {
        mChatRepository.getTestJson().observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mChatView.showWaitDialog();
                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {
                        mChatView.dismissDialog();
                        mChatView.showTestJson(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void subscribe() {
        getChatDatas();
        getTestJson();

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
