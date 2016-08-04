package com.taskworld.support.v7.appcompat

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