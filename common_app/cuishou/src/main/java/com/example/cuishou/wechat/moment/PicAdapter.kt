package com.example.cuishou.wechat.moment

import android.support.v7.widget.RecyclerView
import com.bankcomm.ui.adapter.BGARecyclerViewAdapter
import com.bankcomm.ui.adapter.BGAViewHolderHelper
import com.bumptech.glide.Glide
import com.example.cuishou.R

class PicAdapter(recyclerView: RecyclerView?, defaultItemLayoutId: Int) :
        BGARecyclerViewAdapter<PicBean,RecyclerView>(recyclerView, defaultItemLayoutId) {
//    public  var isIdleState:Boolean = true;

    override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: PicBean?) {
        val imageView = helper!!.getImageView(R.id.life_pic)
//        val gridLayoutManager = mRecyclerView.layoutManager as GridLayoutManager
//        val findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()
//        val findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition()+gridLayoutManager.spanCount
//        val isVisible = position in findFirstVisibleItemPosition..findLastVisibleItemPosition
//        if (isIdleState) {
            Glide.with(mContext).load(model!!.path)/*.skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)*/.into(imageView)
//        }
//        Log.v("scroll","isIdleState:$isIdleState")
    }
}