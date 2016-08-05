package com.taskworld.kxandroid

import android.app.Activity
import android.app.AlertDialog
import android.app.DownloadManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.os.PowerManager
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import java.io.Serializable

val Context.inputMethodManager: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val Context.layoutInflater: LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

val Context.connectivityManager: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

val Context.powerManager: PowerManager
    get() = getSystemService(Context.POWER_SERVICE) as PowerManager

val Context.downloadManager: DownloadManager
    get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

fun Context.dpToPx(dp: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics);
fun Context.pxToDp(px: Int) = (px * (resources.displayMetrics.density) + 0.5)
fun Context.spToPx(sp: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), resources.displayMetrics);

fun Context.alert(init: AlertDialog.Builder.() -> Unit) {
    val builder = AlertDialog.Builder(this)
    builder.init()
    val dialog = builder.show()

    // Hack to make dialog have 80% width of screen
    val lp = WindowManager.LayoutParams()
    lp.copyFrom(dialog.window.attributes)
    lp.width = (getScreenWidth(this) * 0.8f).toInt()
    dialog.window.attributes = lp

}

inline fun <reified T : Any> Context.createIntent(params: List<Pair<String, Any>> = listOf(),
                                                  action: String? = null, flags: Int = -1): Intent {
    val intent = Intent(this, T::class.java)
    intent.action = action

    if (flags != -1) {
        intent.flags = flags
    }

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
    return intent
}

inline fun <reified T : Service> Context.startService(vararg params: Pair<String, Any>) {
    startService(createIntent<T>(params.toList()))
}

fun Context.hideKeyboard(view: View?) {
    view?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun Context.showKeyboard(view: View) {
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun Context.openBrowser(link: String) {
    val uri = Uri.parse(link)
    startActivity(Intent(Intent.ACTION_VIEW, uri))
}

fun Context.download(title: String, src: Uri, dest: Uri): Long {
    val request = DownloadManager.Request(src)
    request.setDestinationUri(dest)
    request.setTitle(title)
    return downloadManager.enqueue(request)
}

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any>, action: String? = null, flags: Int = -1) {
    startActivity(createIntent<T>(params.toList(), action = action, flags = flags))
}

inline fun <reified T : Activity> Activity.startActivityForResult(vararg params: Pair<String, Any>, action: String? = null, flags: Int = -1, requestCode: Int = 0) {
    startActivityForResult(createIntent<T>(params.toList(), action = action, flags = flags), requestCode)
}