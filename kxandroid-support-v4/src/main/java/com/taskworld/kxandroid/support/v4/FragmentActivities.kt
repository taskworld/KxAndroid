package com.taskworld.kxandroid.support.v4

import android.content.Context
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Parcelable
import android.support.v4.app.FragmentActivity
import android.support.v4.app.SharedElementCallback
import android.view.View

/**
 * Created by tipatai on 4/26/16 AD.
 */

fun FragmentActivity.kx_finishAfterTransition() {
    if (KxVersion.LOLLIPOP) {
        finishAfterTransition()
    } else {
        finish()
    }
}