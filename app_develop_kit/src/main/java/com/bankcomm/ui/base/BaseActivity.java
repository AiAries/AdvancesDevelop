package com.bankcomm.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.widget.Toast;

import com.bankcomm.framework.log.AriesLog;
import com.bankcomm.framework.utils.ActivityUtils;
import com.bankcomm.ui.view.swipe.SwipeBackActivity;

/**
 * Created by  on 2018/6/14.
 *
 * BaseActivity
 *  --ShadeBaseActivity
 *      --RetrofitApiBaseActivity
 */

public abstract class BaseActivity extends SwipeBackActivity {
    public final String TAG = this.getClass().getSimpleName();

    public enum ToastLength {
        LONG, SHORT
    }
    protected <T extends Fragment> T findOrCreateViewFragment(Class<T> fragmentClazz, int
            contentFrame) {
        T tasksFragment =
                (T) getSupportFragmentManager().findFragmentById(contentFrame);
        boolean isCreated = false;
        if (tasksFragment == null) {
            // Create the fragment
            try {
                tasksFragment = fragmentClazz.newInstance();
                isCreated = true;
            } catch (Exception e) {
                e.printStackTrace();
                return  null;
            }
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, contentFrame);
        }
        AriesLog.d("findOrCreateViewFragment",isCreated?"created":"retained");
        return tasksFragment;
    }
    protected void toast(@NonNull String msg) {
        toast(msg,null);
    }
    protected void toast(@NonNull String msg, @Nullable  ToastLength len) {
        toast(msg,len,false);
    }

    protected void toast(@NonNull String msg, @Nullable ToastLength len,boolean inCenter) {
        if (len ==null) {
            len = ToastLength.SHORT;
        }
        Toast toast = Toast.makeText(this, msg, len == ToastLength.SHORT ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.setGravity(inCenter?Gravity.CENTER:Gravity.NO_GRAVITY,0,0);
        toast.show();
    }
    /*
    处理activity异常关闭的情况，保存用户输入的数据
     */

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
    /*
    获取activity异常关闭保存的用户的数据
     */

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
}
