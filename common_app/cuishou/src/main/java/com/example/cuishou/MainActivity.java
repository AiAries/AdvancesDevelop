package com.example.cuishou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cuishou.Test1.TestActivity;
import com.example.cuishou.Test2.TestActivity2;
import com.example.cuishou.Test3.TestActivity3;
import com.example.cuishou.wechat.moment.MomentActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    /**多个*/
    public void onClickView(View v){
         startActivity(new Intent(this, TestActivity.class));
    }
    /**单个*/
    public void onClickView2(View v){
        startActivity(new Intent(this, TestActivity2.class));

    }
    /**单个*/
    public void onClickView3(View v){
        startActivity(new Intent(this, TestActivity3.class));

    }

    public void onClickView4(View view) {
        startActivity(new Intent(this, MomentActivity.class));
    }
}
