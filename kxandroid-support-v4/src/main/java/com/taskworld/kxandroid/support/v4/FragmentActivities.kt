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

class KxSharedElementCallback {

    var onSharedElementStart: ((MutableList<String>?, MutableList<View>?, MutableList<View>?) -> Unit)? = null
    var onSharedElementEnd: ((MutableList<String>?, MutableList<View>?, MutableList<View>?) -> Unit)? = null
    var onMapSharedElements: ((MutableList<String>?, MutableMap<String, View>?) -> Unit)? = null
    var onRejectSharedElements: ((MutableList<View>?) -> Unit)? = null
    var onCreateSnapshotView: ((Context?, Parcelable?) -> Unit)? = null
    var onCaptureSharedElementSnapshot: ((View?, Matrix?, RectF?) -> Unit)? = null

}

fun FragmentActivity.kx_setExitSharedElementCallback(callbackInit: KxSharedElementCallback.() -> Unit) {
    val callback = KxSharedElementCallback().apply { callbackInit() }
    if (KxVersion.LOLLIPOP) {
        setExitSharedElementCallback(object : SharedElementCallback() {

            override fun onSharedElementStart(sharedElementNames: MutableList<String>?, sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
                callback.onSharedElementStart?.invoke(sharedElementNames, sharedElements, sharedElementSnapshots)
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots)
            }

            override fun onSharedElementEnd(sharedElementNames: MutableList<String>?, sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
                callback.onSharedElementEnd?.invoke(sharedElementNames, sharedElements, sharedElementSnapshots)
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
            }

            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                callback.onMapSharedElements?.invoke(names, sharedElements)
                super.onMapSharedElements(names, sharedElements)
            }

            override fun onRejectSharedElements(rejectedSharedElements: MutableList<View>?) {
                callback.onRejectSharedElements?.invoke(rejectedSharedElements)
                super.onRejectSharedElements(rejectedSharedElements)
            }

            override fun onCreateSnapshotView(context: Context?, snapshot: Parcelable?): View? {
                callback.onCreateSnapshotView?.invoke(context, snapshot)
                return super.onCreateSnapshotView(context, snapshot)
            }

            override fun onCaptureSharedElementSnapshot(sharedElement: View?, viewToGlobalMatrix: Matrix?, screenBounds: RectF?): Parcelable? {
                callback.onCaptureSharedElementSnapshot?.invoke(sharedElement, viewToGlobalMatrix, screenBounds)
                return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds)
            }
        })
    }
}

fun FragmentActivity.kx_setEnterSharedElementCallback(callbackInit: KxSharedElementCallback.() -> Unit) {
    val callback = KxSharedElementCallback().apply { callbackInit() }
    if (KxVersion.LOLLIPOP) {
        setEnterSharedElementCallback(object : SharedElementCallback() {

            override fun onSharedElementStart(sharedElementNames: MutableList<String>?, sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
                callback.onSharedElementStart?.invoke(sharedElementNames, sharedElements, sharedElementSnapshots)
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots)
            }

            override fun onSharedElementEnd(sharedElementNames: MutableList<String>?, sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
                callback.onSharedElementEnd?.invoke(sharedElementNames, sharedElements, sharedElementSnapshots)
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
            }

            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                callback.onMapSharedElements?.invoke(names, sharedElements)
                super.onMapSharedElements(names, sharedElements)
            }

            override fun onRejectSharedElements(rejectedSharedElements: MutableList<View>?) {
                callback.onRejectSharedElements?.invoke(rejectedSharedElements)
                super.onRejectSharedElements(rejectedSharedElements)
            }

            override fun onCreateSnapshotView(context: Context?, snapshot: Parcelable?): View? {
                callback.onCreateSnapshotView?.invoke(context, snapshot)
                return super.onCreateSnapshotView(context, snapshot)
            }

            override fun onCaptureSharedElementSnapshot(sharedElement: View?, viewToGlobalMatrix: Matrix?, screenBounds: RectF?): Parcelable? {
                callback.onCaptureSharedElementSnapshot?.invoke(sharedElement, viewToGlobalMatrix, screenBounds)
                return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds)
            }
        })
    }
}

fun FragmentActivity.kx_finishAfterTransition() {
    if (KxVersion.LOLLIPOP) {
        finishAfterTransition()
    } else {
        finish()
    }
}