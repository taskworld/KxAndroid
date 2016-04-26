package com.taskworld.kxandroid.support.v4

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.view.View

/**
 * Created by tipatai on 3/7/16 AD.
 */

fun View.animate(init: ViewPropertyAnimatorCompat.() -> Unit): ViewPropertyAnimatorCompat {
    val animator = ViewCompat.animate(this)
    animator.init()
    animator.start()
    return animator
}