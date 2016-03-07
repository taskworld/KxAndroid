package com.taskworld.kxandroid

import android.animation.Animator

/**
 * Created by verachadw on 7/10/2015 AD.
 */

fun Animator.onAnimateState(init: _Animator_AnimatorListener.() -> Unit) {
    val listener = _Animator_AnimatorListener()
    listener.init()
    addListener(listener)
}

class _Animator_AnimatorListener : Animator.AnimatorListener {

    private var onAnimationRepeat: ((Animator?) -> Unit)? = null
    private var onAnimationEnd: ((Animator?) -> Unit)? = null
    private var onAnimationCancel: ((Animator?) -> Unit)? = null
    private var onAnimationStart: ((Animator?) -> Unit)? = null

    override final fun onAnimationRepeat(animation: Animator?) {
        onAnimationRepeat?.invoke(animation)
    }

    override final fun onAnimationEnd(animation: Animator) {
        onAnimationEnd?.invoke(animation)
    }

    override final fun onAnimationCancel(animation: Animator?) {
        onAnimationCancel?.invoke(animation)
    }

    override final fun onAnimationStart(animation: Animator?) {
        onAnimationStart?.invoke(animation)
    }

    fun animationRepeat(listener: ((Animator?) -> Unit)?) {
        onAnimationRepeat = listener
    }

    fun animationEnd(listener: ((Animator?) -> Unit)?) {
        onAnimationEnd = listener
    }

    fun animationCancel(listener: ((Animator?) -> Unit)?) {
        onAnimationCancel = listener
    }

    fun animationStart(listener: ((Animator?) -> Unit)?) {
        onAnimationStart = listener
    }

}

