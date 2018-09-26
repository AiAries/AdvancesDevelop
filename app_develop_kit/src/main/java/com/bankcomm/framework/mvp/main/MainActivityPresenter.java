package com.bankcomm.framework.mvp.main;

import android.content.Context;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private Context mContext;
    private MainActivityContract.View mView;

    public MainActivityPresenter(Context context, MainActivityContract.View view) {
        this.mContext = context;
        this.mView = view;
    }
}