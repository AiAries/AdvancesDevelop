package com.example.cuishou.wechat.moment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView

/**
 * 缩放，向下拖拽退出
 */
@SuppressLint("AppCompatCustomView")
class DragImageView(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs) {

    var isInterception: Boolean = true
    private var startDownX: Float = 0f
    private var startDownY: Float = 0f
    private var animalStart = false
    private var mLastMovePoint  = PointF()
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.v(TAG,"action: ${event?.actionMasked}")
        if (isInterception) {
            when (event?.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
//                    startDownX = event.rawX
//                    startDownY = event.rawY
//                    Log.v(TAG,"MotionEvent.ACTION_DOWN: ${event.rawX}")
                    animalStart = true
                    mLastMovePoint.x = event.x
                    mLastMovePoint.y = event.y
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    val x = event.x
                    val y = event.y
                    doAnimation(x-mLastMovePoint.x,y-mLastMovePoint.y)
                    invalidate()
                    mLastMovePoint.x = x
                    mLastMovePoint.y = y
                    return true
                }

                MotionEvent.ACTION_UP -> {
                    animalStart = false
                    return true
                }

            }
        }
        return true
    }
    private fun doAnimation(rawX: Float, rawY: Float) {

        val objAnimal1 = ObjectAnimator.ofFloat(this,"translationY",rawY)
        val objAnimal2 = ObjectAnimator.ofFloat(this,"translationX",rawX)
        objAnimal1.interpolator = DecelerateInterpolator(1.0f)
        objAnimal2.interpolator = DecelerateInterpolator(1.0f)
//        objAnimal1.duration = 200
//        objAnimal1.start()

        val animSet =AnimatorSet()
        animSet.duration = 10
        animSet.startDelay = 0
        animSet.playTogether(objAnimal1, objAnimal2)
        animSet.start()
    }

//    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
//        TODO("Not yet implemented")
//    }

    companion object {
        val TAG =DragImageView::class.java.simpleName
        /**
         * 惯性动画衰减参数
         */
        const val FLING_DAMPING_FACTOR = 0.9f
    }


}