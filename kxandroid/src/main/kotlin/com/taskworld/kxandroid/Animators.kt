package com.taskworld.kxandroid

import android.animation.Animator

fun Animator.onAnimateState(init: _Animator_AnimatorListener.() -> Unit) {
    val listener = _Animator_AnimatorListener()
    listener.init()
    addListener(listener)
}

//private stuff
class _Animator_AnimatorListener : Animator.AnimatorListener {

    private var onAnimationRepeat: ((Animator?) -> Unit)? = null
    private var onAnimationEnd: ((Animator?) -> Unit)? = null
    private var onAnimationCancel: ((Animator?) -> Unit)? = null
    private var onAnimationStart: ((Animator?) -> Unit)? = null

    override fun onAnimationRepeat(animation: Animator?) {
        onAnimationRepeat?.invoke(animation)
    }

    override fun onAnimationEnd(animation: Animator) {
        onAnimationEnd?.invoke(animation)
    }

    override fun onAnimationCancel(animation: Animator?) {
        onAnimationCancel?.invoke(animation)
    }

    override fun onAnimationStart(animation: Animator?) {
        onAnimationStart?.invoke(animation)
    }

    fun onAnimationRepeat(listener: ((Animator?) -> Unit)?) {
        onAnimationRepeat = listener
    }

    fun onAnimationEnd(listener: ((Animator?) -> Unit)?) {
        onAnimationEnd = listener
    }

    fun onAnimationCancel(listener: ((Animator?) -> Unit)?) {
        onAnimationCancel = listener
    }

    fun onAnimationStart(listener: ((Animator?) -> Unit)?) {
        onAnimationStart = listener
    }

}

