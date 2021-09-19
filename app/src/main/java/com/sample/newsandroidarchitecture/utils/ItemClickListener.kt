package com.sample.newsandroidarchitecture.utils

import android.view.View
import android.widget.ImageView

interface ItemClickListener<T> {

    fun onClick(imageView: View, data: List<T>, position: Int)
}