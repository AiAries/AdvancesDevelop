package com.example.cuishou.wechat.moment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * 只在Idle状态加载RecyclerView未加载的item
 * <p></p>
 */
class IdleLoadPicRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    public var isIdleState = true
    private var isScrollUp = true
    private var mFirstVisibleItemPosition: Int = -1
    private var mLastVisibleItemPosition: Int = -1
    private lateinit var listener: OnScrollListener
//    init {
//        addOnScrollListener(object : OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                isScrollUp = dy>0
//                Log.v("IdleLoadPicRecyclerView","onScrolled:$dy")
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                val b = RecyclerView.SCROLL_STATE_IDLE == newState
//                //记录滚动的状态
//                isIdleState = b
//                if (b) {
//                    val linearLayoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
//                    val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
//                    val lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
//                    if (isScrollUp) {
//                        val unloadFirstViewPosition: Int = getUnloadFirstViewPosition(firstVisibleItemPosition)
//                        Log.v("idle", "firstVisibleItemPosition:$firstVisibleItemPosition\n" +
//                                "lastVisibleItemPosition:$lastVisibleItemPosition" + "unloadFirstViewPosition:$unloadFirstViewPosition")
//                        if (unloadFirstViewPosition > lastVisibleItemPosition) {
//                            return
//                        }
//                        recyclerView.adapter!!.notifyItemRangeChanged(unloadFirstViewPosition,
//                                lastVisibleItemPosition - unloadFirstViewPosition + 1, false)
//
//                    } else {
//                        val unloadLastViewPosition: Int = getUnloadLastViewPosition(lastVisibleItemPosition)
//                        Log.v("idle", "firstVisibleItemPosition:$firstVisibleItemPosition\n" +
//                                "lastVisibleItemPosition:$lastVisibleItemPosition\n" + "unloadLastViewPosition:$unloadLastViewPosition")
//                        if (unloadLastViewPosition < firstVisibleItemPosition) {
//                            return
//                        }
//                        recyclerView.adapter!!.notifyItemRangeChanged(firstVisibleItemPosition,
//                                unloadLastViewPosition - firstVisibleItemPosition + 3, true)
//
//                    }
//
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
//            private fun getUnloadLastViewPosition(lastVisibleItemPosition: Int): Int {
//                var a = lastVisibleItemPosition
//                while (a in mFirstVisibleItemPosition..mLastVisibleItemPosition) {
//                    a--
//                }
//                return a
//            }
//        })
//    }
}