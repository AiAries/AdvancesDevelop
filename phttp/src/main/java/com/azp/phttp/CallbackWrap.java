package com.azp.phttp;

import com.common.Log;

public class CallbackWrap<T>  implements  Callback<T>{
    private Callback<T> callback;
    public CallbackWrap(Callback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onError(final Exception e) {
        if (callback != null) {
            Log.Instance().WriteLog(e.getMessage());
            AppExecutors.getInstance().mainThread().execute(new Runnable() {
                @Override
                public void run() {
                    callback.onError(e);
                }
            });

        }
    }

    @Override
    public void onSuccess(final T o) {
        if (callback!=null) {
            Log.Instance().WriteLog(o.toString());
            AppExecutors.getInstance().mainThread().execute(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess(o);
                }
            });

        }
    }
}
