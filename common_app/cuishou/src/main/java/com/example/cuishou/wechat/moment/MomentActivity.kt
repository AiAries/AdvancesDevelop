package com.example.cuishou.wechat.moment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bankcomm.ui.adapter.BGARecyclerViewAdapter
import com.example.cuishou.R

class MomentActivity : AppCompatActivity() {
    
    private lateinit var  mAdapter:BGARecyclerViewAdapter<MomentDynamicInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_moment).apply {
            layoutManager = LinearLayoutManager(context)

        }
        mAdapter = MomentAdapter(recyclerView,R.layout.item_moment)
        recyclerView.adapter = mAdapter
    }

    override fun onResume() {
        super.onResume()
        var moments:MutableList<MomentDynamicInfo> = getMoment()
        mAdapter.data = moments
    }

    private fun getMoment(): MutableList<MomentDynamicInfo> {
        val moments: MutableList<MomentDynamicInfo> = arrayListOf()
        for (i in 1..10) {
            val element = MomentDynamicInfo("jack${i}", "abcdefg", "")
            moments.add(element)
        }
        return moments
        
    }
}
