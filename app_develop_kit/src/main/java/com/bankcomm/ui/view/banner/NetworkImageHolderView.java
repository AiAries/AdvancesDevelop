/*
package com.bankcomm.ui.view.banner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bankcomm.framework.app.analysis.event.EventCollectUtil;
import com.bankcomm.framework.app.analysis.event.EventVo;
import com.bankcomm.maidanba.MyApplication;
import com.bankcomm.maidanba.R;
import com.bankcomm.maidanba.account.AccountHelper;
import com.bankcomm.maidanba.activity.WebViewActivity;
import com.bankcomm.maidanba.consts.Constans;
import com.bankcomm.maidanba.utils.WebViewUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yitong.sdk.utils.StringUtil;
import com.yitong.sdk.utils.android.AndroidUtil;

import java.util.HashMap;

*/
/**
 * Created by Sai on 15/8/4. 网络图片加载例子
 *//*

public class NetworkImageHolderView implements CBPageAdapter.Holder<HashMap<String, String>> {
    private ImageView imageView;
    private int n;
    Context mainActivity;
    private String cityLatitude = "";
    private String cityLongitude = "";
    private String cityId = "";
    private boolean isFromRestaurant;

    public NetworkImageHolderView(Context mainActivity, int n, String cityLatitude, String cityLongitude, String cityId, boolean isFromRestaurant) {
        this.mainActivity = mainActivity;
        this.n = n;
        this.cityLatitude = cityLatitude;
        this.cityLongitude = cityLongitude;
        this.cityId = cityId;
        this.isFromRestaurant = isFromRestaurant;
    }

    @Override
    public View createView(Context context) {
        // 你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        FrameLayout f= new FrameLayout(context);
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        f.addView(imageView);
        return f;
    }

    @Override
    public void UpdateUI(final Context context, final int position, final HashMap<String, String> data) {
        imageView.setImageResource(R.drawable.load_image_maxh);
       */
/* if (n == 1) {

            if(Build.VERSION.SDK_INT>=23){
                Glide.with(context.getApplicationContext()).load(data.get("imgUrl")).placeholder(R.drawable.load_image_maxh).error(R.drawable.load_image_maxh).into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
            }else{
                Glide.with(context.getApplicationContext()).load(data.get("imgUrl"))
                        .placeholder(R.drawable.load_image_maxh).error(R.drawable.load_image_maxh)
                        .into(imageView); }
        } else if (n == 2){
            if(Build.VERSION.SDK_INT>=23){
                Glide.with(context.getApplicationContext()).load(data.get("imgUrl")).placeholder(R.drawable.load_image_maxh).error(R.drawable.load_image_maxh).into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
            }else{
                Glide.with(context.getApplicationContext()).load(data.get("imgUrl"))
                        .placeholder(R.drawable.load_image_maxh).error(R.drawable.load_image_maxh)
                        .into(imageView);
            }

        }else {
            if(Build.VERSION.SDK_INT>=23){
                Glide.with(context.getApplicationContext()).load(data.get("imgUrl")).placeholder(R.drawable.load_image_maxh).error(R.drawable.load_image_maxh).into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
            }else{
                Glide.with(context.getApplicationContext()).load(data.get("imgUrl"))
                        .placeholder(R.drawable.load_image_maxh).error(R.drawable.load_image_maxh)
                        .into(imageView);
            }


        }*//*

        if(Build.VERSION.SDK_INT>=23){
            Glide.with(MyApplication.getAppContext()).load(data.get("imgUrl")).placeholder(R.drawable.load_image_maxh).error(R.drawable.load_image_maxh).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    imageView.setImageDrawable(resource);
                }
            });
        }else{
            Glide.with(MyApplication.getAppContext()).load(data.get("imgUrl"))
                    .placeholder(R.drawable.load_image_maxh).error(R.drawable.load_image_maxh)
                    .into(imageView); }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AndroidUtil.isFastClick()) {//防止快速点击
                    return;//如果重复点击不做反应
                }
//                StatisticsUtil.pushEventWithEventId(Constants.EVENT_ID_MAIN_HOME_BANNER_BASE +(position+1));
                String paramId = "";
                String name = "";
                if (StringUtil.isNotEmpty(data.get("moduleParamId"))) {
                    paramId = data.get("moduleParamId");
                }
                if (StringUtil.isNotEmpty(data.get("name"))) {
                    name = data.get("name");
                }

                if (StringUtil.isNotEmpty(data.get("moduleCode"))) {
                    EventVo eventVo = new EventVo();
                    eventVo.setMODULE(data.get("moduleCode"));
                    eventVo.setLOG_ID(Constans.logId);
                    eventVo.setEVENT_TYPE("01");
                    eventVo.setUSER_ID(AccountHelper.getInstance().getUserId());//用户ID
                    eventVo.setREMARKS("");
                    eventVo.setIS_SUCCESS("1");

                    EventCollectUtil.getInstance().onEvent(eventVo);
                }

                Intent intent = new Intent(mainActivity, WebViewActivity.class);
                Bundle bundle = new Bundle();

                if (StringUtil.isNotEmpty(data.get("menuUrl")) && WebViewUtils.distUrl((Activity) context, data.get("menuUrl"), null, paramId,cityLatitude,cityLongitude,cityId,isFromRestaurant,name)) {
                    return;
                } else if (StringUtil.isNotEmpty(data.get("menuUrl"))) {
                    bundle.putString("web_url", data.get("menuUrl"));
                    intent.putExtras(bundle);
                    mainActivity.startActivity(intent);
                }

            }
        });
    }
}
*/
