package ${packageName};

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ${activityClass} extends AppCompatActivity implements ${activityClass}Contract.View {

    private ${activityClass}Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.${layoutName});

        mPresenter = new ${activityClass}Presenter(this, this);
    }
}