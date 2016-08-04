package com.taskworld.kxandroid

import android.net.ConnectivityManager

fun ConnectivityManager.isActiveNetworkAvailable(): Boolean {
    val networkInfo = activeNetworkInfo
    return (networkInfo?.type == ConnectivityManager.TYPE_WIFI) || (networkInfo?.type == ConnectivityManager.TYPE_MOBILE)
}