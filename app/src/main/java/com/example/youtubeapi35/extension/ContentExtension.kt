package com.example.youtubeapi35.extension

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.net.URL

fun ImageView.loadImage(url: String){
    Glide.with(context)
        .load(url)
        .into(this)
}