package cn.com.codequality.business.custom_view;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bankcomm.ui.adapter.BGARecyclerViewDefaultAdapter;
import com.bankcomm.ui.adapter.BGAViewHolderHelper;
import com.bankcomm.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import cn.com.codequality.R;
import cn.com.codequality.business.databinding.BindDemoActivity;

public class CustomViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        RecyclerView customList = findViewById(R.id.custom_view_list);
        customList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        BGARecyclerViewDefaultAdapter<CustomViewBean> adapter = new BGARecyclerViewDefaultAdapter<CustomViewBean>(customList, R.layout.item_custom_view) {

            @Override
            protected void fillData(BGAViewHolderHelper helper, int position, CustomViewBean model) {
                helper.getTextView(R.id.custom_view_name).setText(model.customViewName);
            }
        };
        customList.setAdapter(adapter);
        //init data
        List<CustomViewBean> data = initData();

        adapter.setData(data);
    }

    private List<CustomViewBean> initData() {
        List<CustomViewBean> data = new ArrayList<>();
        CustomViewBean e = new CustomViewBean();
        e.setCustomViewName("圆形背景，打标文字");
        e.setCustomView2ActivityClazz(BindDemoActivity.class);
        data.add(e);
        return data;
    }

    class CustomViewBean {
        /**
         * 自定义view的简称
         */
        String customViewName;
        /**
         * 自定义view展示效果所在的地方
         */
        String customView2Activity;
        /**
         * 自定义view展示效果所在的地方
         */
        Class customView2ActivityClazz;

        public String getCustomViewName() {
            return customViewName;
        }

        public void setCustomViewName(String customViewName) {
            this.customViewName = customViewName;
        }

        public String getCustomView2Activity() {
            return customView2Activity;
        }

        public void setCustomView2Activity(String customView2Activity) {
            this.customView2Activity = customView2Activity;
        }

        public Class getCustomView2ActivityClazz() {
            return customView2ActivityClazz;
        }

        public void setCustomView2ActivityClazz(Class customView2ActivityClazz) {
            this.customView2ActivityClazz = customView2ActivityClazz;
        }
    }
}
