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
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                val b = RecyclerView.SCROLL_STATE_IDLE == newState
//                mAdapter.isIdle = b
//                if (b) {
////                    var para = recyclerView.getChildAt(0).layoutParams as RecyclerView.LayoutParams
////                    var position = para.viewAdapterPosition
//                    val linearLayoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
//                    val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
//                    val lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
//                    val unloadFirstViewPosition: Int = getUnloadFirstViewPosition(firstVisibleItemPosition)
//                    if (unloadFirstViewPosition > lastVisibleItemPosition) {
//                        return
//                    }
//                    recyclerView.adapter!!.notifyItemRangeChanged(unloadFirstViewPosition,
//                            lastVisibleItemPosition - unloadFirstViewPosition + 1, false)
//                    Log.v("idle", "firstVisibleItemPosition:$firstVisibleItemPosition\n" +
//                            "lastVisibleItemPosition:$lastVisibleItemPosition" + "unloadFirstViewPosition:$unloadFirstViewPosition")
////                    recyclerView.adapter!!.notifyDataSetChanged()
//                    mFirstVisibleItemPosition = firstVisibleItemPosition
//                    mLastVisibleItemPosition = lastVisibleItemPosition
//                }
//                Log.v("scroll", "newState:$newState")
//            }
//
//            private fun getUnloadFirstViewPosition(firstVisibleItemPosition: Int): Int {
//                var a = firstVisibleItemPosition
//                while (a in mFirstVisibleItemPosition..mLastVisibleItemPosition) {
//                    a++
//                }
//                return a
//            }
//        })
//    }
//
//    private var mFirstVisibleItemPosition: Int = -1
//    private var mLastVisibleItemPosition: Int = -1
    }
    override fun onResume() {
        super.onResume()
        var moments: MutableList<MomentDynamicInfo> = getMoment()
        mAdapter.data = moments
    }

    private fun getMoment(): MutableList<MomentDynamicInfo> {
        val moments: MutableList<MomentDynamicInfo> = arrayListOf()

        for (i in 1..40) {
            val element = MomentDynamicInfo("jack${i}", "上传到中间件的数据包有三种，外访、协查、协查补件", "")
            moments.add(element)
        }
        return moments

    }
}
