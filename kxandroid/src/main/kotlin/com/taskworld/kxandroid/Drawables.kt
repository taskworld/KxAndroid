package com.taskworld.kxandroid

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable

fun Drawable.tintDrawableWithColor(color: Int) {
    this.clearColorFilter()
    this.setColorFilter(color, PorterDuff.Mode.SRC_IN)
}
