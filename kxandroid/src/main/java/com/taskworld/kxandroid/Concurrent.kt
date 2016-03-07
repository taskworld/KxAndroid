package com.taskworld.kxandroid

/**
 * Created by Johnny Dew on 1/18/16.
 */

fun runOnBackgroundThread(f: () -> Unit) {
    Thread() {
        f()
    }.start()
}

