package com.example.cuishou.wechat.moment

import android.os.Bundle
import android.widget.ImageView
import com.bankcomm.ui.base.BaseActivity
import com.bumptech.glide.Glide
import com.example.cuishou.R

class DragImageActivity: BaseActivity() {
    private lateinit var mImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_img_preview)
        mImageView = findViewById<ImageView>(R.id.imageView)
        setSwipeBackEnable(false)
    }

    override fun onResume() {
        super.onResume()
        val imgUrl = intent.getStringExtra("imgUrl")
        Glide.with(applicationContext).load(imgUrl).into(mImageView)
    }
}