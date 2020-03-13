package com.example.cuishou.wechat.moment

import android.support.v7.widget.RecyclerView
import com.bankcomm.ui.adapter.BGARecyclerViewAdapter
import com.bankcomm.ui.adapter.BGAViewHolderHelper
import com.bumptech.glide.Glide
import com.example.cuishou.R

class PicAdapter(recyclerView: RecyclerView?, defaultItemLayoutId: Int) :
        BGARecyclerViewAdapter<PicBean>(recyclerView, defaultItemLayoutId) {
    override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: PicBean?) {
        val imageView = helper!!.getImageView(R.id.life_pic)
        Glide.with(mContext).load(model!!.path).asBitmap().into(imageView)
    }
}