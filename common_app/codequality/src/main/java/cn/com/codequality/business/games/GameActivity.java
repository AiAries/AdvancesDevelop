package cn.com.codequality.business.games;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bankcomm.framework.log.AresLog;

import cn.com.codequality.R;


public class GameActivity extends AppCompatActivity {

    private String name = "zhangsan";
    private static final String TAG = "GameActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        RetrofitApi.getIntance().getBuilder().baseUrl("url");
//        ConfigConstant
        int w = 3;
        int business = 0;
        AresLog.d(TAG, "onCreate: ");
        AresLog.i(TAG, "onCreate: business" + business);
        AresLog.d(TAG, "onCreate: w " + w);
        Log.d(TAG, "onCreate() returned: " + savedInstanceState);
        final EditText bracket = (EditText) findViewById(R.id.bracket);
        final TextView express = (TextView) findViewById(R.id.express);
        final EditText game_num = (EditText) findViewById(R.id.game_num);
        final EditText num1 = (EditText) findViewById(R.id.num1);
        final EditText num2 = (EditText) findViewById(R.id.num2);
        final EditText num3 = (EditText) findViewById(R.id.num3);
        final EditText num4 = (EditText) findViewById(R.id.num4);
        /*num1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                int action = keyEvent.getAction();
                if (action==KeyEvent.KEYCODE_NAVIGATE_NEXT) {
                    num2.requestFocus();
                }
                return false;
            }
        });
        num2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                int action = keyEvent.getAction();
                if (action==KeyEvent.KEYCODE_NAVIGATE_NEXT) {
                    num3.requestFocus();
                }
                return false;
            }
        });
        num3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                int action = keyEvent.getAction();
                if (action==KeyEvent.KEYCODE_NAVIGATE_NEXT) {
                    num4.requestFocus();
                }
                return false;
            }
        });*/

         findViewById(R.id.calc).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 try {
                     float[] cards = new float[4];
                     cards[0] = Float.valueOf(num1.getText().toString().trim());
                     cards[1] = Float.valueOf(num2.getText().toString().trim());
                     cards[2] = Float.valueOf(num3.getText().toString().trim());
                     cards[3] = Float.valueOf(num4.getText().toString().trim());
                     String s = bracket.getText().toString();
                     s = TextUtils.isEmpty(s) ? "1" : s;
                     boolean hasResult = Card36.getCard36ByInputNum(cards, "1".equals(s));
                     if (hasResult) {
                         Toast.makeText(GameActivity.this, "bingo ，这个可以算出36的值", Toast.LENGTH_LONG)
                                 .show
                                         ();
                         express.setText(Card36.express);
                     } else {
                         Toast.makeText(GameActivity.this, "赶紧换一组牌吧，兄弟们", Toast.LENGTH_SHORT).show();
                     }
                 } catch (Exception e) {
                     System.out.println(e.getMessage());
                 }
             }
         });

        findViewById(R.id.clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bracket.setText("");
                num1.setText("");
                num2.setText("");
                num3.setText("");
                num4.setText("");
                express.setText("");
                game_num.setText("");
            }
        });
        findViewById(R.id.setting_num).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = game_num.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    return;
                }
                Card36.rightResult = Integer.parseInt(trim);
            }
        });
             findViewById(R.id.translation).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(GameActivity.this,TrainActivity.class);
                     startActivity(intent);
                 }
             });
    }

}
