package cn.com.codequality.business.databinding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankcomm.ui.base.BaseFragment;

import cn.com.codequality.databinding.BindFragBinding;

public class BindDemoFragment extends BaseFragment {

    private DemoViewModel viewModel;

    public void setViewModel(DemoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BindFragBinding fragBinding = BindFragBinding.inflate(inflater, container, false);
        fragBinding.setViewmodel(viewModel);
        return fragBinding.getRoot();
    }
}
