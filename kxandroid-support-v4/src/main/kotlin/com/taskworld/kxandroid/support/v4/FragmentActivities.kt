package com.taskworld.kxandroid.support.v4

import android.support.v4.app.FragmentActivity

fun FragmentActivity.kx_finishAfterTransition() {
    if (KxVersion.LOLLIPOP) {
        finishAfterTransition()
    } else {
        finish()
    }
}