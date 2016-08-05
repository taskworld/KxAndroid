package com.taskworld.kxandroid

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

val Activity.rootView: ViewGroup
    get() = window.decorView.findViewById(android.R.id.content) as ViewGroup

fun <T : View> Activity.bindView(id: Int): T {
    val view = findViewById(id) ?:
            throw IllegalArgumentException("Given ID $id could not be found in $this!")
    @Suppress("unchecked_cast")
    return view as T
}

fun <T : View> Activity.bindOptionalView(id: Int): T? {
    val view = findViewById(id)
    @Suppress("unchecked_cast")
    return view as? T
}

inline fun <reified T : Activity> Activity.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any>) {
    startActivityForResult(createIntent<T>(params.toList()), requestCode)
}

fun Activity.toast(text: String?) {
    if (text == null) return
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Activity.inflate(resourceId: Int, root: ViewGroup?, attachToRoot: Boolean): View {
    return LayoutInflater.from(this).inflate(resourceId, root, attachToRoot)
}

fun Activity.inflate(resourceId: Int, root: ViewGroup?): View {
    return LayoutInflater.from(this).inflate(resourceId, root)
}

inline fun <reified T : Activity> Activity.createIntentTo(): Intent {
    return Intent(this, T::class.java)
}

