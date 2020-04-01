package cn.com.codequality.business.handlerloop;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.com.codequality.R;

public class HandlerLoopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_loop);
    }
    static Handler handler;
    private Runnable runnable;
    @Override
    protected void onResume() {
        super.onResume();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.v("handler", "::: start" );
                Looper.prepare();
                handler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        Log.v("handler", ":::" + msg.what);
                    }
                };
                Log.v("handler", "::: loop" );
//                Looper.loop();
                Log.v("handler", "::: end" );
            }
        };
        new Thread(runnable).start();

    }
    private int count;
    public void sendMessage(View view) {
        Message message = handler.obtainMessage();
        message.what = count++;
        handler.sendMessage(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        Message msg = handler.obtainMessage();
        msg.what = 66;
        handler.sendMessageDelayed(msg,1000);
        Log.v("handler", "::: onDestroy" );
    }
}
