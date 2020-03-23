package com.example.cuishou.wechat.moment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.cuishou.R

class DragImageActivity: AppCompatActivity() {
    private lateinit var mImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_img_preview)
        mImageView = findViewById<ImageView>(R.id.imageView)
    }

    override fun onResume() {
        super.onResume()
        val imgUrl = intent.getStringExtra("imgUrl")
        Glide.with(applicationContext).load(imgUrl).into(mImageView)
    }
}