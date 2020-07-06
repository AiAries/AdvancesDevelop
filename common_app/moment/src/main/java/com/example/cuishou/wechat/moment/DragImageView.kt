package com.example.cuishou.wechat.moment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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
    private var beforeMoveX = 0
    private var beforeMoveY = 0
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.v(TAG,"action: ${event?.actionMasked}")
        if (isInterception) {
            when (event?.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
//                    startDownX = event.rawX
//                    startDownY = event.rawY
                    animalStart = true
                    startDownX = event.x.roundToInt()
                    startDownY = event.y.roundToInt()
                    beforeMoveX = startDownX
                    beforeMoveY = startDownY
                    Log.v(TAG,"MotionEvent.ACTION_DOWN:beforeMoveX $beforeMoveX ::: beforeMoveY$beforeMoveY")
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    val curMoveX = event.x
                    val curMoveY = event.y
                    doAnimation(curMoveX,curMoveY)
                    Log.v(TAG,"MotionEvent.ACTION_MOVE:curMoveX $curMoveX ::: curMoveY$curMoveY")
                    scrollBy(((beforeMoveX-curMoveX).roundToInt()), ((beforeMoveY-curMoveY).roundToInt()))
                    beforeMoveX = curMoveX.roundToInt()
                    beforeMoveY = curMoveY.roundToInt()
                    return true
                }

                MotionEvent.ACTION_UP -> {
//                    doAnimation(0f,0f)
//                    scrollBy(((beforeMoveX-startDownX)), ((beforeMoveY-startDownY)))
                    animalStart = false
                    return true
                }

            }
        }
        return true
    }
    private fun doAnimation(curMoveX: Float, curMoveY: Float) {
        val displayMetrics = context.resources.displayMetrics
//        var sx = 1- rawX/displayMetrics.heightPixels
//        sx = if (sx>=1) 1f else sx
//        sx = if (sx<=0.4f) 0.4f else sx

        val scaleMinValue = 0.6f
        var sy = 1- (curMoveY-startDownY)/displayMetrics.heightPixels
        sy = if (sy>=1) 1f else sy
        sy = if (sy<=scaleMinValue) scaleMinValue else sy
        val objAnimal1 = ObjectAnimator.ofFloat(this,"translationY",curMoveY-beforeMoveY.toFloat()).apply {
            duration = 0
        }
        val objAnimal2 = ObjectAnimator.ofFloat(this,"translationX",curMoveX-beforeMoveX.toFloat()).apply {
            duration = 0
        }
        Log.v("doAnimation",":::scaleY $sy")
        Log.v("doAnimation","curMoveY $curMoveY :::beforeMoveY $beforeMoveY")
        val scaleX = ObjectAnimator.ofFloat(this,"scaleX",sy)
        val scaleY = ObjectAnimator.ofFloat(this,"scaleY",sy)
        scaleX.interpolator = DecelerateInterpolator(1.0f)
        scaleY.interpolator = DecelerateInterpolator(1.0f)

        val animSet =AnimatorSet()
        animSet.duration = 0
        animSet.startDelay = 0
        if (sy <= scaleMinValue) {
//            animSet.playTogether(objAnimal1,objAnimal2)
        } else {
//            animSet.playTogether(scaleX, scaleY,objAnimal1,objAnimal2)
        }
        animSet.playTogether(scaleX,scaleY)
//        animSet.playTogether(objAnimal1,objAnimal2)
        animSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                beforeMoveX = curMoveX.roundToInt()
                beforeMoveY = curMoveY.roundToInt()
            }
        })
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