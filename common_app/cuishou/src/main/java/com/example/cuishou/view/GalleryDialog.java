package com.example.cuishou.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bankcomm.ui.adapter.BGAOnRVItemClickListener;
import com.bankcomm.ui.adapter.BGARecyclerViewAdapter;
import com.bankcomm.ui.adapter.BGAViewHolderHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cuishou.R;

import java.util.List;

public class GalleryDialog extends Dialog {


    private final BGARecyclerViewAdapter<PicBean> adapter;
    private final View takePicView;

    public List<PicBean> getData() {
        return data;
    }

    public void setData(List<PicBean> data) {
        this.data = data;
        adapter.setData(data);
        if (data.size() >= 5) {
            takePicView.setVisibility(View.GONE);
        } else {
            takePicView.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    private List<PicBean> data;

    public GalleryDialog(@NonNull Context context, final OnRVItemClickListener callback, View.OnClickListener listener) {
        this(context, R.style.tipDialog,callback,listener);

    }
    public GalleryDialog(@NonNull Context context, int theme, final OnRVItemClickListener callback, View.OnClickListener listener) {
        super(context, theme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_gallery, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rc_gallery);
        takePicView = view.findViewById(R.id.take_pic);
        takePicView.setOnClickListener(listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        adapter = new BGARecyclerViewAdapter<PicBean>(recyclerView, R.layout.item_dialog_gallery) {
            @Override
            protected void fillData(BGAViewHolderHelper helper, int position, PicBean model) {
                ImageView imageView = helper.getView(R.id.iv_gallery);
                Glide.with(mContext).load(model.path).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(imageView);

            }
        };
        adapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                callback.onRVItemClick(parent, itemView, position);
            }
        });
        recyclerView.setAdapter(adapter);
        setContentView(view);
    }

    public interface OnRVItemClickListener {
        void onRVItemClick(ViewGroup parent, View itemView, int position);
    }

}
