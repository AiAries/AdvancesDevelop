package cn.com.codequality.business.databinding;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class DemoViewModel extends BaseObservable {
    public ObservableBoolean display = new ObservableBoolean();
    public ObservableField<String> title = new ObservableField<>();
    private OnViewAction onViewAction;

    public void setOnViewAction(OnViewAction onViewAction) {
        this.onViewAction = onViewAction;
    }

    public interface OnViewAction{
        void onBtnClick();
    }

    public void showOrHide(){
        if (onViewAction!=null) {
            onViewAction.onBtnClick();
        }
    }
}
