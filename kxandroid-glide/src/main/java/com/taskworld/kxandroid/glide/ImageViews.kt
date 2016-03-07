package com.taskworld.kxandroid.glide

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by VerachadW on 4/29/15.
 */

fun ImageView.setImageUrl(url: String, defaultResId: Int = 0, errorResId: Int = 0) {
    val creator = Glide.with(context).load(url)

    if (defaultResId != 0) {
        creator.placeholder(defaultResId)
    }

    if (errorResId != 0) {
        creator.error(errorResId)
    }

    creator.into(this)
}