package com.example.cuishou.wechat.moment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.cuishou.R

class MomentActivity : AppCompatActivity() {

    private lateinit var mAdapter: MomentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: IdleLoadPicRecyclerView = findViewById<IdleLoadPicRecyclerView>(R.id.rv_moment).apply {
            layoutManager = LinearLayoutManager(context)

        }
        mAdapter = MomentAdapter(recyclerView, R.layout.item_moment)
        recyclerView.adapter = mAdapter
    }
    override fun onResume() {
        super.onResume()
        var moments: MutableList<MomentDynamicInfo> = getMoment()
        mAdapter.data = moments
    }

    private fun getMoment(): MutableList<MomentDynamicInfo> {
        val moments: MutableList<MomentDynamicInfo> = arrayListOf()

        for (i in 1..25) {
            val element = MomentDynamicInfo("jack${i}", "上传到中间件的数据包有三种，外访、协查、协查补件", "")
            moments.add(element)
        }
        return moments

    }
}
