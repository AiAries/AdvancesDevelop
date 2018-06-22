package com.bankcomm.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bankcomm.ui.helper.shade.IShade;
import com.bankcomm.ui.helper.shade.ProgressShadeImp;

/**
 * Created by A170860 on 2018/6/14.
 * 带遮罩的网络请求的Activity
 */

public abstract class ShadeBaseActivity extends BaseActivity {
    protected IShade mShade;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShade = new ProgressShadeImp(this);
    }
}
