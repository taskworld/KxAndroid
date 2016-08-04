package com.taskworld.kxandroid.support.v4

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat

fun Context.getDrawableFromResource(drawableId: Int, colorResId: Int = -1): Drawable {
    var drawable = ContextCompat.getDrawable(this, drawableId)
    if (colorResId != -1) {
        drawable.setColorFilter(ContextCompat.getColor(this, colorResId), PorterDuff.Mode.SRC_IN)
    }
    return drawable
}