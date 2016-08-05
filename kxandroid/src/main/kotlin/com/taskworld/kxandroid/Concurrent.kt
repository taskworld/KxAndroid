package com.taskworld.kxandroid

fun runOnBackgroundThread(f: () -> Unit) {
    Thread() {
        f()
    }.start()
}

