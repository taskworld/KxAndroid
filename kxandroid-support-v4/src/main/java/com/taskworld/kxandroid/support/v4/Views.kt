package com.taskworld.kxandroid.support.v4

import android.support.v4.util.Pair as PairV4
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

fun View.kx_makeSharedElementPair(transitionName: String? = null): PairV4<View, String> {
    transitionName?.let { kx_setTransitionName(transitionName) }
    return PairV4.create(this, kx_getTransitionName())
}

fun View.kx_getTransitionName(): String = if (KxVersion.LOLLIPOP) transitionName else ""

fun View.kx_setTransitionName(transitionName: String) {
    if (KxVersion.LOLLIPOP) this.transitionName = transitionName
}