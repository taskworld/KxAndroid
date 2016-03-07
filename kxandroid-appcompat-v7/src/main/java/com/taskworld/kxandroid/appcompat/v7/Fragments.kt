package com.taskworld.kxandroid.appcompat.v7

/**
 * Created by tipatai on 3/7/16 AD.
 */

import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.ContextThemeWrapper

fun Fragment.alert(style: Int, init: AlertDialog.Builder .() -> Unit) {
    val contextWrapper = ContextThemeWrapper(context, style)
    val builder = AlertDialog.Builder(contextWrapper)
    builder.init()
    builder.create()
    builder.show()
}