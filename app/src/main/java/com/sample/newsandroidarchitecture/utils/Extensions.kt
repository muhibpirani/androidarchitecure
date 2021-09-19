package com.sample.newsandroidarchitecture.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sample.newsandroidarchitecture.R

fun ImageView.loadImg(
    imageUrl: String?,
    placeHolder: Int? = R.drawable.ic_baseline_placeholder,
    roundedCorner: Boolean = true,
    scaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_XY
) {
    val loadUrl = imageUrl
    var loadScaleType = scaleType

    val reqCreator = Glide.with(this).load(loadUrl)

    if (roundedCorner)
        reqCreator.transform(RoundedCorners(16))

    reqCreator
        .placeholder(R.drawable.ic_baseline_placeholder)
        .error(R.drawable.ic_baseline_placeholder)
        .into(this)

    Glide.with(this)
        .load(loadUrl)
        .into(this)

    setScaleType(loadScaleType)
}