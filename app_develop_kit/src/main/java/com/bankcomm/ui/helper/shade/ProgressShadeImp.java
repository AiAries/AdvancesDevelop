package com.bankcomm.ui.helper.shade;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.bankcomm.ui.view.dialogs.fragment.ProgressDialogFragment;

/**
 * Created by A170860 on 2018/6/14.
 */

public class ProgressShadeImp implements IShade {

    private AppCompatActivity mActivity;
    private ProgressDialogFragment.ProgressDialogBuilder builder;
    private DialogFragment dialogFragment;

    public ProgressShadeImp(AppCompatActivity activity) {
        builder = ProgressDialogFragment.createBuilder(mActivity, mActivity.getSupportFragmentManager());
        mActivity = activity;
    }

    @Override
    public void showDialog() {
        //TODO 等待实现
        dialogFragment = builder.show();
    }

    @Override
    public void hideDialog() {
        //TODO 等待实现
        dialogFragment.dismiss();
    }
}
