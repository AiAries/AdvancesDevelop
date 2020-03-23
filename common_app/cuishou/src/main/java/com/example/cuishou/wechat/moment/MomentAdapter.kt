package com.example.cuishou.wechat.moment

import android.annotation.SuppressLint
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.bankcomm.ui.adapter.BGARecyclerViewAdapter
import com.bankcomm.ui.adapter.BGAViewHolderHelper
import com.example.cuishou.R
import java.io.File

class MomentAdapter(recyclerView: IdleLoadPicRecyclerView?, defaultItemLayoutId: Int)
    : BGARecyclerViewAdapter<MomentDynamicInfo,IdleLoadPicRecyclerView>(recyclerView, defaultItemLayoutId) {
//    public  var isIdle = true
    override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: MomentDynamicInfo?) {
        helper!!.getTextView(R.id.name)!!.text = model!!.name
        helper.getTextView(R.id.content)!!.text = model.content
        val rvPicList = helper.getView<RecyclerView>(R.id.pics).apply {
//            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            layoutManager = GridLayoutManager(context, 3,GridLayoutManager.HORIZONTAL,false)

        }
        val picAdapter = PicAdapter(rvPicList, R.layout.item_moment_pic)
        rvPicList.adapter = picAdapter
        picAdapter.data = getPic(position)
//        picAdapter.isIdleState = mRecyclerView.isIdleState
//        Log.v("scroll","isIdle:${picAdapter.isIdleState}")

    }
    @SuppressLint("SdCardPath")
    private fun getPic(position: Int): MutableList<PicBean> {
        val moments: MutableList<PicBean> = arrayListOf()
        val picDir = File("/sdcard/pic/")
        val list = picDir.listFiles()
        var nums = generateRandom9Num(arrayListOf(),position);
        nums.forEach() {
            val element = PicBean(list!![it].absolutePath)
                moments.add(element)
        }
//        list!!.forEach { i ->
//            val element = PicBean(i.absolutePath)
//            if (moments.size<9) {
//                moments.add(element)
//            }
//        }
        return moments

    }
    private fun generateRandom9Num(args: MutableList<Int>, position: Int): MutableList<Int> {
        var i:Int = 0
//        var dd = intArrayOf(0,0,0,0,0,0,0,0,0)
        while (i < 9){
            var element = if((position + i)>25) 25 else position + i
            args.add(element)
            i++
        }
        return args;
    }
}