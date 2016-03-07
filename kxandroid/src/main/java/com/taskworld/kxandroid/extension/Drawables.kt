package com.taskworld.kxandroid.extension

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable

/**
 * Created by verachadw on 7/10/2015 AD.
 */

fun Drawable.tintDrawableWithColor(color: Int) {
    this.clearColorFilter()
    this.setColorFilter(color, PorterDuff.Mode.SRC_IN)
}
