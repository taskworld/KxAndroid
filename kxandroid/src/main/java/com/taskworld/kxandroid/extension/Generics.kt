package com.taskworld.kxandroid.extension

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import timber.log.Timber

/**
 * Created by Kittinun Vantasin on 10/18/14.
 */

inline fun <reified T : Any> T.logD(msg: Any, tag: String = tag()) {
    Timber.tag(tag)
    Timber.d(msg.toString())
}

inline fun <reified T : Any> T.logI(msg: Any, tag: String = tag()) {
    Timber.tag(tag)
    Timber.i(msg.toString())
}

inline fun <reified T : Any> T.logV(msg: Any, tag: String = tag()) {
    Timber.tag(tag)
    Timber.v(msg.toString())
}

inline fun <reified T : Any> T.logW(msg: Any, tag: String = tag(), exception: Throwable? = null) {
    Timber.tag(tag)
    val handle = { Timber.w(msg.toString()) }

    exception?.let {
        Timber.w(exception, msg.toString())
    } ?: handle()
}

inline fun <reified T : Any> T.logE(msg: Any, tag: String = tag()) {
    Timber.tag(tag)
    Timber.e(msg.toString())
}

fun getScreenWidth(context: Context) = getSizeOfDisplay(context).x

fun getScreenHeight(context: Context) = getSizeOfDisplay(context).y

private fun getSizeOfDisplay(context: Context): Point {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    var p = Point()
    display.getSize(p)
    return p
}

inline fun <reified T : Any> T.tag() = T::class.java.simpleName

fun <T> unSafeLazy(initializer: () -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE, initializer)
}
