package com.taskworld.kxandroid.support.v4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.taskworld.kxandroid.createIntent
import java.io.Serializable

fun Fragment.toast(text: String?): Unit {
    if (text == null) return
    Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
}

fun Fragment.inflate(resourceId: Int, root: ViewGroup?, attachToRoot: Boolean): View {
    return LayoutInflater.from(activity).inflate(resourceId, root, attachToRoot)
}

fun Fragment.inflate(resourceId: Int, root: ViewGroup?): View {
    return LayoutInflater.from(activity).inflate(resourceId, root)
}

inline fun <reified T : Activity> Fragment.createIntentTo(): Intent {
    return Intent(activity, T::class.java)
}

@Suppress("unchecked_cast")
inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any>) {
    val intent = Intent(activity, T::class.java)
    for (pair in params) {
        val (key, value) = pair
        when (value) {
            is Boolean -> intent.putExtra(key, value)
            is Byte -> intent.putExtra(key, value)
            is Char -> intent.putExtra(key, value)
            is Short -> intent.putExtra(key, value)
            is Int -> intent.putExtra(key, value)
            is Long -> intent.putExtra(key, value)
            is Float -> intent.putExtra(key, value)
            is Double -> intent.putExtra(key, value)
            is String -> intent.putExtra(key, value)
            is CharSequence -> intent.putExtra(key, value)
            is Parcelable -> intent.putExtra(key, value)
            is Serializable -> intent.putExtra(key, value)
            is BooleanArray -> intent.putExtra(key, value)
            is ByteArray -> intent.putExtra(key, value)
            is CharArray -> intent.putExtra(key, value)
            is DoubleArray -> intent.putExtra(key, value)
            is FloatArray -> intent.putExtra(key, value)
            is IntArray -> intent.putExtra(key, value)
            is LongArray -> intent.putExtra(key, value)
            is ShortArray -> intent.putExtra(key, value)
            is Bundle -> intent.putExtra(key, value)
            is Array<*> -> {
                when (value[0]) {
                    is String -> intent.putExtra(key, value as Array<String>)
                    is CharSequence -> intent.putExtra(key, value as Array<CharSequence>)
                    is Parcelable -> intent.putExtra(key, value as Array<Parcelable>)
                }
            }
        }
    }
    startActivity(intent)
}

@Suppress("unchecked_cast")
fun Fragment.withArguments(vararg params: Pair<String, Any>): Fragment {
    val bundle = Bundle()
    for ((key, value) in params) {
        when (value) {
            is Boolean -> bundle.putBoolean(key, value)
            is Byte -> bundle.putByte(key, value)
            is Char -> bundle.putChar(key, value)
            is Short -> bundle.putShort(key, value)
            is Int -> bundle.putInt(key, value)
            is Long -> bundle.putLong(key, value)
            is Float -> bundle.putFloat(key, value)
            is Double -> bundle.putDouble(key, value)
            is String -> bundle.putString(key, value)
            is CharSequence -> bundle.putCharSequence(key, value)
            is Parcelable -> bundle.putParcelable(key, value)
            is Serializable -> bundle.putSerializable(key, value)
            is BooleanArray -> bundle.putBooleanArray(key, value)
            is ByteArray -> bundle.putByteArray(key, value)
            is CharArray -> bundle.putCharArray(key, value)
            is DoubleArray -> bundle.putDoubleArray(key, value)
            is FloatArray -> bundle.putFloatArray(key, value)
            is IntArray -> bundle.putIntArray(key, value)
            is LongArray -> bundle.putLongArray(key, value)
            is ShortArray -> bundle.putShortArray(key, value)
            is Bundle -> bundle.putBundle(key, value)
            is Array<*> -> {
                when (value[0]) {
                    is String -> bundle.putStringArray(key, value as Array<String>)
                    is CharSequence -> bundle.putCharSequenceArray(key, value as Array<CharSequence>)
                    is Parcelable -> bundle.putParcelableArray(key, value as Array<Parcelable>)
                }
            }
        }
    }
    arguments = bundle
    return this
}

inline fun <reified T : Activity> Fragment.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any>) {
    startActivityForResult(activity.createIntent<T>(params.toList()), requestCode)
}

