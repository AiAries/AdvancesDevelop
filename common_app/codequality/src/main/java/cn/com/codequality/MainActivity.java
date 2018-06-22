package cn.com.codequality;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ArrayList list;
        int e = 2 ;
        e++;
        Log.d("", "onCreate: " + e);
        switch (e) {
            case 1:
                break;
            case 2:
                float a = 3;
                break;
            default:
                break;
        }
        while (true) {
            Log.d("", "onCreate: " + e);
        }
    }
}
