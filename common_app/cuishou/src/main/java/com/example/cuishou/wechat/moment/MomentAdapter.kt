package com.example.cuishou.wechat.moment

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.bankcomm.ui.adapter.BGARecyclerViewAdapter
import com.bankcomm.ui.adapter.BGAViewHolderHelper
import com.example.cuishou.R
import java.io.File

class MomentAdapter(recyclerView: RecyclerView?, defaultItemLayoutId: Int)
    : BGARecyclerViewAdapter<MomentDynamicInfo>(recyclerView, defaultItemLayoutId) {
    override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: MomentDynamicInfo?) {
        helper!!.getTextView(R.id.name)!!.text = model!!.name
        helper.getTextView(R.id.content)!!.text = model.content
        val rvPicList = helper.getView<RecyclerView>(R.id.pics).apply {
//            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            layoutManager = GridLayoutManager(context, 3,GridLayoutManager.HORIZONTAL,false)

        }
        val picAdapter = PicAdapter(rvPicList, R.layout.item_moment_pic)
        rvPicList.adapter = picAdapter
        picAdapter.data = getPic()
    }
    private fun getPic(): MutableList<PicBean> {
        val moments: MutableList<PicBean> = arrayListOf()
        val picDir = File("/sdcard/TJB/Merchant/company/1/companyphoto/pic")
        val list = picDir.listFiles()
        list!!.forEach { i ->
            val element = PicBean(i.absolutePath)
            moments.add(element)
        }
        return moments

    }
}