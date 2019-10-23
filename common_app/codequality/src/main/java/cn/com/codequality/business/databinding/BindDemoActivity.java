package cn.com.codequality.business.databinding;

import android.os.Bundle;

import com.bankcomm.ui.base.BaseDataBindingActivity;

import cn.com.codequality.R;


public class BindDemoActivity extends BaseDataBindingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_demo);
        final BindDemoFragment fragment = findOrCreateViewFragment(BindDemoFragment.class, R.id.content_frame);
        final DemoViewModel demoViewModel = findOrCreateViewModel(DemoViewModel.class, "BindDemoActivityTAG");
        //给viewmodel设置获取数据仓库
//        demoViewModel.setDataReposity();
        // Link View and DemoViewModel
        fragment.setViewModel(demoViewModel);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    demoViewModel.title.set("i am good boy");
                    demoViewModel.display.set(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }



}
