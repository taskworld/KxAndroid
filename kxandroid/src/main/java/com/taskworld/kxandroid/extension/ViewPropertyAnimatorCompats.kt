package com.taskworld.kxandroid.extension

import android.support.v4.view.ViewPropertyAnimatorCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View

/**
 * Created by Kittinun Vantasin on 4/24/15.
 */

fun ViewPropertyAnimatorCompat.onAnimationListener(init: _ViewPropertyAnimatorListener.() -> Unit): ViewPropertyAnimatorCompat {
    val listener = _ViewPropertyAnimatorListener()
    listener.init()
    setListener(listener)
    return this
}

//private stuffs
class _ViewPropertyAnimatorListener : ViewPropertyAnimatorListener {

    var onAnimationStart: ((View?) -> Unit)? = null
    var onAnimationEnd: ((View?) -> Unit)? = null
    var onAnimationCancel: ((View?) -> Unit)? = null

    override fun onAnimationStart(view: View?) {
        onAnimationStart?.invoke(view)
    }

    //proxy method
    fun animationStart(listener: ((View?) -> Unit)?) {
        onAnimationStart = listener
    }

    override fun onAnimationEnd(view: View?) {
        onAnimationEnd?.invoke(view)
    }

    //proxy method
    fun animationEnd(listener: ((View?) -> Unit)?) {
        onAnimationEnd = listener
    }

    override fun onAnimationCancel(view: View?) {
        onAnimationCancel?.invoke(view)
    }

    //proxy method
    fun animationCancel(listener: ((View?) -> Unit)?) {
        onAnimationCancel = listener
    }

}