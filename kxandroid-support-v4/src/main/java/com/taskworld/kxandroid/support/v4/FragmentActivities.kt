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

class _FragmentActivity_SharedElementCallback : SharedElementCallback() {

    private var _sharedElementStart: ((MutableList<String>?, MutableList<View>?, MutableList<View>?) -> Unit)? = null
    private var _sharedElementEnd: ((MutableList<String>?, MutableList<View>?, MutableList<View>?) -> Unit)? = null
    private var _mapSharedElements: ((MutableList<String>?, MutableMap<String, View>?) -> Unit)? = null
    private var _rejectSharedElements: ((MutableList<View>?) -> Unit)? = null
    private var _createSnapshotView: ((Context?, Parcelable?) -> View)? = null
    private var _captureSharedElementSnapshot: ((View?, Matrix?, RectF?) -> Parcelable)? = null

    fun sharedElementStart(listener: ((MutableList<String>?, MutableList<View>?, MutableList<View>?) -> Unit)?) {
        _sharedElementStart = listener
    }

    fun sharedElementEnd(listener: ((MutableList<String>?, MutableList<View>?, MutableList<View>?) -> Unit)?) {
        _sharedElementEnd = listener
    }

    fun mapSharedElements(listener: ((MutableList<String>?, MutableMap<String, View>?) -> Unit)?) {
        _mapSharedElements = listener
    }

    fun rejectSharedElements(listener: ((MutableList<View>?) -> Unit)?) {
        _rejectSharedElements = listener
    }

    fun createSnapshotView(listener: ((Context?, Parcelable?) -> View)?) {
        _createSnapshotView = listener
    }

    fun captureSharedElementSnapshot(listener: ((View?, Matrix?, RectF?) -> Parcelable)?) {
        _captureSharedElementSnapshot = listener
    }


    override fun onSharedElementStart(sharedElementNames: MutableList<String>?, sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
        _sharedElementStart?.invoke(sharedElementNames, sharedElements, sharedElementSnapshots)
    }

    override fun onSharedElementEnd(sharedElementNames: MutableList<String>?, sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
        _sharedElementEnd?.invoke(sharedElementNames, sharedElements, sharedElementSnapshots)
    }

    override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
        _mapSharedElements?.invoke(names, sharedElements)
    }

    override fun onRejectSharedElements(rejectedSharedElements: MutableList<View>?) {
        _rejectSharedElements?.invoke(rejectedSharedElements)
    }

    override fun onCreateSnapshotView(context: Context?, snapshot: Parcelable?): View? {
        return _createSnapshotView?.invoke(context, snapshot)
    }

    override fun onCaptureSharedElementSnapshot(sharedElement: View?, viewToGlobalMatrix: Matrix?, screenBounds: RectF?): Parcelable? {
        return _captureSharedElementSnapshot?.invoke(sharedElement, viewToGlobalMatrix, screenBounds)
    }

}

fun FragmentActivity.kx_setExitSharedElementCallback(callbackInit: _FragmentActivity_SharedElementCallback.() -> Unit) {
    val callback = _FragmentActivity_SharedElementCallback().apply { callbackInit() }
    if (KxVersion.LOLLIPOP) {
        setExitSharedElementCallback(callback)
    }
}

fun FragmentActivity.kx_setEnterSharedElementCallback(callbackInit: _FragmentActivity_SharedElementCallback.() -> Unit) {
    val callback = _FragmentActivity_SharedElementCallback().apply { callbackInit() }
    if (KxVersion.LOLLIPOP) {
        setEnterSharedElementCallback(callback)
    }
}

fun FragmentActivity.kx_finishAfterTransition() {
    if (KxVersion.LOLLIPOP) {
        finishAfterTransition()
    } else {
        finish()
    }
}