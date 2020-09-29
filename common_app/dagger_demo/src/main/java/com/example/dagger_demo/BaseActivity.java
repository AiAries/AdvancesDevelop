package com.example.dagger_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dagger.android.AndroidInjection;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
    }
}