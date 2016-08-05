package com.taskworld.kxandroid.support.v4

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.view.View

fun View.animate(init: ViewPropertyAnimatorCompat.() -> Unit): ViewPropertyAnimatorCompat {
    return ViewCompat.animate(this).apply {
        init()
        start()
    }
}