package com.example.cuishou.wechat.moment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log

/**
 * 只在Idle状态加载RecyclerView未加载的item
 * <p></p>
 */
class IdleLoadPicRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    public var isIdleState = true
    private var mFirstVisibleItemPosition: Int = -1
    private var mLastVisibleItemPosition: Int = -1
//    private lateinit var listener: OnScrollListener
    init {
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val b = RecyclerView.SCROLL_STATE_IDLE == newState
                isIdleState = b
                if (b) {
//                    var para = recyclerView.getChildAt(0).layoutParams as RecyclerView.LayoutParams
//                    var position = para.viewAdapterPosition
                    val linearLayoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                    val lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
                    val unloadFirstViewPosition: Int = getUnloadFirstViewPosition(firstVisibleItemPosition)
                    if (unloadFirstViewPosition > lastVisibleItemPosition) {
                        return
                    }
                    recyclerView.adapter!!.notifyItemRangeChanged(unloadFirstViewPosition,
                            lastVisibleItemPosition - unloadFirstViewPosition + 1, false)
                    Log.v("idle", "firstVisibleItemPosition:$firstVisibleItemPosition\n" +
                            "lastVisibleItemPosition:$lastVisibleItemPosition" + "unloadFirstViewPosition:$unloadFirstViewPosition")
//                    recyclerView.adapter!!.notifyDataSetChanged()
                    mFirstVisibleItemPosition = firstVisibleItemPosition
                    mLastVisibleItemPosition = lastVisibleItemPosition
                }
                Log.v("scroll", "newState:$newState")
            }

            private fun getUnloadFirstViewPosition(firstVisibleItemPosition: Int): Int {
                var a = firstVisibleItemPosition
                while (a in mFirstVisibleItemPosition..mLastVisibleItemPosition) {
                    a++
                }
                return a
            }
        })
    }
}