package com.example.cuishou.wechat.moment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.cuishou.R

class DragImageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_img_preview)
        val imgBig = findViewById<ImageView>(R.id.imageView)
        Glide.with(applicationContext).load("").into(imgBig)
    }
}