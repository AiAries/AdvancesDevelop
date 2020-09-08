package cn.com.codequality.business.jetpack_exper.work;

import android.os.Bundle;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.bankcomm.ui.base.BaseActivity;

import cn.com.codequality.R;

/**
 * 1.将work添加到你的工程中
 */
public class WorkManagerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);
        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWork.class).build();
        WorkManager.getInstance(this).enqueue(uploadWorkRequest);
    }
}
