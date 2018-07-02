package cn.com.codequality.business.games;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cn.com.codequality.R;


/**
 * Created by A170860 on 2018/6/19.
 */

public class TrainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        get36Card();
        v();
    }

    private void get36Card() {
        //1.得出正确的 随机四张牌 可以组成36
        //2.30秒之后，重新随机四张可以组成36的数
        new Thread() {
            @Override
            public void run() {
                super.run();

            }
        }.start();
    }

    private void v() {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Random r = new Random();
                int a = r.nextInt(10) + 1;
                int b = r.nextInt(10) + 1;
                int c = r.nextInt(10) + 1;
                int d = r.nextInt(10) + 1;
                float[] cards = new float[4];
                cards[0] = a;
                cards[1] = b;
                cards[2] = c;
                cards[3] = d;
                boolean hasResult = Card36.getCard36ByInputNum(cards, true);
                if (hasResult) {
                    Toast.makeText(TrainActivity.this, "bingo ，这个可以算出36的值", Toast.LENGTH_LONG)
                            .show();
//                    express.setText(Card36.express);
                } else {
                    Toast.makeText(TrainActivity.this, "赶紧换一组牌吧，兄弟们", Toast.LENGTH_SHORT).show();
                }
            }
        };
        timer.schedule(task, 1000, 1000 * 30);

    }
}
