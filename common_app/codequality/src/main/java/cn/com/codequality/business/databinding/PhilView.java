package cn.com.codequality.business.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PhilView extends View {

    private static final String TAG = "调试";

    private OnViewChangeListener mOnViewChangeListener;

    public PhilView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //继承自Android，该方法在Android的View的visibility变化时候回调
    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.d(TAG, "onVisibilityChanged");

        if (mOnViewChangeListener != null) {
            mOnViewChangeListener.onChange();
        }
    }

    @BindingAdapter(value = "display", requireAll = false)
    public static void setDisplay(PhilView view, boolean isDisplay) {
        if (getDisplay(view) == isDisplay) {
            //防止死循环

            Log.d(TAG, "重复设置");
        } else {
            Log.d(TAG, "setDisplay " + isDisplay);
            view.setVisibility(isDisplay ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @InverseBindingAdapter(attribute = "display", event = "displayAttrChanged")
    public static boolean getDisplay(PhilView view) {
        Log.d(TAG, "getDisplay:" + view.getVisibility());
        return view.getVisibility() == View.VISIBLE;
    }

    @BindingAdapter(value = {"displayAttrChanged"}, requireAll = false)
    public static void setDisplayAttrChanged(PhilView view, final InverseBindingListener inverseBindingListener) {
        Log.d(TAG, "setDisplayAttrChanged");

        if (inverseBindingListener == null) {
            view.setViewChangeListener(null);
            Log.d(TAG, "setViewChangeListener(null)");
        } else {
            view.setViewChangeListener(new OnViewChangeListener() {

                @Override
                public void onChange() {
                    Log.d(TAG, "setDisplayAttrChanged -> onChange");
                    inverseBindingListener.onChange();
                }
            });
        }
    }

    private interface OnViewChangeListener {
        void onChange();
    }

    private void setViewChangeListener(OnViewChangeListener listener) {
        this.mOnViewChangeListener = listener;
    }

//    原文：https://blog.csdn.net/zhangphil/article/details/77649256
}
