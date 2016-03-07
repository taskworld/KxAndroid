package com.taskworld.kxandroid.extension

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

/**
 * Created by Kittinun Vantasin on 10/18/14.
 */

fun <T : View> View.bindView(id: Int): T {
    val view = findViewById(id) ?:
            throw IllegalArgumentException("Given ID $id could not be found in $this!")
    @Suppress("unchecked_cast")
    return view as T
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.dpToPx(dp: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics);
fun View.pxToDp(px: Int) = (px * (resources.displayMetrics.density) + 0.5)
fun View.spToPx(sp: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), resources.displayMetrics);

fun View.animate(init: ViewPropertyAnimatorCompat.() -> Unit): ViewPropertyAnimatorCompat {
    val animator = ViewCompat.animate(this)
    animator.init()
    animator.start()
    return animator
}

fun View.requestLayoutCompat() {
    if (android.os.Build.VERSION.SDK_INT >= 18) {
        if (isInLayout) requestLayout()
    } else {
        requestLayout()
    }
}

val View.actualHeight: Int
    get() = topMargin + paddingTop + height + paddingBottom + bottomMargin

val View.actualWidth: Int
    get() = leftMargin + paddingLeft + width + paddingTop + rightMargin

var View.leftMargin: Int
    get() {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        return params.leftMargin
    }
    set(value) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.leftMargin = value
        layoutParams = params
        requestLayoutCompat()
    }

var View.topMargin: Int
    get() {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        return params.topMargin
    }
    set(value) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = value
        layoutParams = params
        requestLayoutCompat()
    }

var View.rightMargin: Int
    get() {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        return params.rightMargin
    }
    set(value) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.rightMargin = value
        layoutParams = params
        requestLayoutCompat()
    }

var View.bottomMargin: Int
    get() {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        return params.bottomMargin
    }
    set(value) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.bottomMargin = value
        layoutParams = params
        requestLayoutCompat()
    }