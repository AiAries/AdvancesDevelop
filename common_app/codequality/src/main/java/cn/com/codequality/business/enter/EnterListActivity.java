package cn.com.codequality.business.enter;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bankcomm.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import cn.com.codequality.R;

public class EnterListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_list);
        List<String> data = initData();
        initView(data);
    }

    private List<String> initData() {
        List<String> data = new ArrayList<>();
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            ActivityInfo[] activities = packageInfo.activities;
            for (ActivityInfo activity : activities) {
                data.add(activity.name);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void initView(List<String> data) {
        RecyclerView activityList = findViewById(R.id.activity_list);
        activityList.setLayoutManager(new LinearLayoutManager(this));
        activityList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .HORIZONTAL));
        EnterListAdapter enterListAdapter = new EnterListAdapter(activityList, R.layout.recycler_chat);
        enterListAdapter.setData(data);
        activityList.setAdapter(enterListAdapter);
    }
}
