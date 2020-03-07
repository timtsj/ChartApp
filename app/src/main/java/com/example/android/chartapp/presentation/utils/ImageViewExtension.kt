package com.example.android.chartapp.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(
    url: String,
    withCenterCrop: Boolean = true,
    placeholder: Int = -1
) {
    val glide = Glide.with(this)
        .load(url)
    if (withCenterCrop) glide.centerCrop()
    if (placeholder != -1) glide.placeholder(placeholder)
        .into(this)
}