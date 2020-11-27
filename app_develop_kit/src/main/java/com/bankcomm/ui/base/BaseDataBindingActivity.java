package com.bankcomm.ui.base;

import androidx.databinding.BaseObservable;

import com.bankcomm.framework.log.AriesLog;
import com.bankcomm.framework.utils.ActivityUtils;

public abstract class BaseDataBindingActivity extends BaseActivity {

    /**
     * 错误: Cannot resolve type for viewHolder
     错误: cannot generate view binders android.databinding.tool.util.LoggedErrorException: failure, see logs for details.
     * 这个方法放到类库里面就报错
     * @param viewModelClazz
     * @param tag
     * @param <T>
     * @return
     */
    protected <T extends BaseObservable> T findOrCreateViewModel(Class<T> viewModelClazz, String
            tag) {
        // In a configuration change we might have a DemoViewModel present. It's retained using the
        // Fragment Manager.
        try {
            @SuppressWarnings("unchecked")
            ViewModelHolder<T> retainedViewModel =
                    (ViewModelHolder<T>) getSupportFragmentManager()
                            .findFragmentByTag(tag);

            if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
                // If the model was retained, return it.
                AriesLog.d("findOrCreateViewModel",retainedViewModel.getViewmodel().getClass().getSimpleName()+"retained");
                return retainedViewModel.getViewmodel();
            } else {
                // There is no DemoViewModel yet, create it.
                T viewModel = viewModelClazz.newInstance();
                // and bind it to this Activity's lifecycle using the Fragment Manager.
                ActivityUtils.addFragmentToActivity(
                        getSupportFragmentManager(),
                        ViewModelHolder.createContainer(viewModel),
                        tag);
                AriesLog.d("findOrCreateViewModel",viewModel.getClass().getSimpleName()+"created");
                return viewModel;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
