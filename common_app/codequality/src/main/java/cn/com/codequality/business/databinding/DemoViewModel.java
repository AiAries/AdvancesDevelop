package cn.com.codequality.business.databinding;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class DemoViewModel extends BaseObservable {
    public ObservableBoolean isDisplay = new ObservableBoolean();
    public ObservableField<String> title = new ObservableField<>();

}
