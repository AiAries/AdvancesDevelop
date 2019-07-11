package cn.com.codequality.business.databinding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bankcomm.ui.base.BaseFragment;

import cn.com.codequality.databinding.BindFragBinding;

public class BindDemoFragment extends BaseFragment {

    private DemoViewModel viewModel;
    private BindFragBinding fragBinding;

    public void setViewModel(DemoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragBinding = BindFragBinding.inflate(inflater, container, false);
//        fragBinding.setViewmodel(viewModel);
        return fragBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragBinding.title.setText("onActivityCreated");
        Toast.makeText(getContext(), viewModel.title.get(), Toast.LENGTH_SHORT).show();
        fragBinding.phil.setVisibility(View.VISIBLE);
    }

}
