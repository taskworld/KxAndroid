package com.taskworld.kxandroid.extension

import android.net.ConnectivityManager

/**
 * Created by Johnny Dew on 7/2/2015 AD.
 */

fun ConnectivityManager.isActiveNetworkAvailable(): Boolean {
    val networkInfo = activeNetworkInfo
    return (networkInfo?.type == ConnectivityManager.TYPE_WIFI) || (networkInfo?.type == ConnectivityManager.TYPE_MOBILE)
}