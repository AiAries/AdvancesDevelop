package com.bankcomm.framework.mvp.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bankcomm.framework.R;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainActivityPresenter(this, this);
    }
}