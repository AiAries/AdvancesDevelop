package com.example.cuishou.wechat.moment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import kotlin.math.roundToInt

/**
 * 缩放，向下拖拽退出
 */
@SuppressLint("AppCompatCustomView")
class DragImageView(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs) {

    var isInterception: Boolean = true
    private var startDownX: Int = 0
    private var startDownY: Int = 0
    private var animalStart = false
    private var upX = 0
    private var upY = 0
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.v(TAG,"action: ${event?.actionMasked}")
        if (isInterception) {
            when (event?.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
//                    startDownX = event.rawX
//                    startDownY = event.rawY
//                    Log.v(TAG,"MotionEvent.ACTION_DOWN: ${event.rawX}")
                    animalStart = true
                    startDownY = event.y.roundToInt()
                    startDownX = event.x.roundToInt()
                    upX = startDownX
                    upY = startDownY
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    val x = event.x
                    val y = event.y
                    doAnimation(x-startDownX,y-startDownY)
                    scrollBy(((upX-x).roundToInt()), ((upY-y).roundToInt()))
                    upX = x.roundToInt()
                    upY = y.roundToInt()
                    return true
                }

                MotionEvent.ACTION_UP -> {
                    doAnimation(0f,0f)
                    scrollBy(((upX-startDownX)), ((upY-startDownY)))
                    animalStart = false
                    return true
                }

            }
        }
        return true
    }
    private fun doAnimation(rawX: Float, rawY: Float) {
        val displayMetrics = context.resources.displayMetrics
        var sx = 1- rawX/displayMetrics.heightPixels
        sx = if (sx>=1) 1f else sx
        sx = if (sx<=0.4f) 0.4f else sx

        var sy = 1- rawY/displayMetrics.widthPixels
        sy = if (sy>=1) 1f else sy
        sy = if (sy<=0.4f) 0.4f else sy
        Log.v("doAnimation","scalex $sx :::scaleY $sy")
        val scaleX = ObjectAnimator.ofFloat(this,"scaleX",1f,sy)
        val scaleY = ObjectAnimator.ofFloat(this,"scaleY",1f,sy)
        scaleX.interpolator = DecelerateInterpolator(1.0f)
        scaleY.interpolator = DecelerateInterpolator(1.0f)
//        objAnimal1.duration = 200
//        objAnimal1.start()
//        val objAnimal1 = ObjectAnimator.ofFloat(this,"translationY",rawY)
//        val objAnimal2 = ObjectAnimator.ofFloat(this,"translationX",rawX)

        val animSet =AnimatorSet()
        animSet.duration = 0
        animSet.startDelay = 0
        animSet.playTogether(scaleX, scaleY)
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