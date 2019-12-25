package com.bankcomm.ui.view.dialogs.shade;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;


/**
 * Created by  on 2018/6/14.
 */

public class ProgressShadeImp implements IShade {

    private DialogFragment dialogFragment;
    private FragmentActivity mActivity;

    public ProgressShadeImp(FragmentActivity activity) {
        mActivity = activity;
        dialogFragment = new ProgressDialogFragment();
    }

    @Override
    public void showDialog() {
        if (dialogFragment==null) {
            return;
        }
        if (dialogFragment.isAdded()) {
            return;
        }
//        dialogFragment.setCancelable(false);
        dialogFragment.show(mActivity.getSupportFragmentManager(),"");
    }

    @Override
    public void hideDialog() {
        if (dialogFragment!=null&&dialogFragment.getShowsDialog()) {
            dialogFragment.dismiss();
        }
    }
}
