package cn.com.codequality.business.chat.detail;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import cn.com.codequality.PatchUtils;

public class MyService extends Service {
    static {
        System.loadLibrary("patch-lib");
    }
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                PatchUtils.patch("/sdcard/old.apk", "/sdcard/new.apk", "/sdcard/patch");
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
