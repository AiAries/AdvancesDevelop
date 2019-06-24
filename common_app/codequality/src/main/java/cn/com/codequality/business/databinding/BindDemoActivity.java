package cn.com.codequality.business.databinding;

import android.os.Bundle;

import com.bankcomm.ui.base.BaseDataBindingActivity;

import cn.com.codequality.R;


public class BindDemoActivity extends BaseDataBindingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_demo);
        BindDemoFragment fragment = findOrCreateViewFragment(BindDemoFragment.class, R.id.content_frame);
        DemoViewModel demoViewModel = findOrCreateViewModel(DemoViewModel.class, "BindDemoActivityTAG");
        //给viewmodel设置获取数据仓库
        //demoViewModel.setDataReposity();
        // Link View and DemoViewModel
        fragment.setViewModel(demoViewModel);
    }



}
