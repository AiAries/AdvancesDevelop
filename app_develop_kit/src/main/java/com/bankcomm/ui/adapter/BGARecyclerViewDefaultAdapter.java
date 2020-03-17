package com.bankcomm.ui.adapter;
import android.support.v7.widget.RecyclerView;

public abstract class BGARecyclerViewDefaultAdapter<M> extends BGARecyclerViewAdapter<M, RecyclerView>{

    public BGARecyclerViewDefaultAdapter(RecyclerView recyclerView, int defaultItemLayoutId) {
        super(recyclerView, defaultItemLayoutId);
    }
}