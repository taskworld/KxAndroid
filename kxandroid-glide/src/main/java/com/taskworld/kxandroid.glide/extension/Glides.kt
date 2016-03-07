package com.taskworld.kxandroid.glide.extension

import com.bumptech.glide.DrawableTypeRequest
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Created by tipatai on 11/20/15 AD.
 */

fun DrawableTypeRequest<String>.onRequestListener(init: _Glide_OnRequestListener.() -> Unit) {
    val requestListener = _Glide_OnRequestListener()
    requestListener.init()
    listener(requestListener)
}

class _Glide_OnRequestListener : RequestListener<String, GlideDrawable> {

    private var onException: ((Exception?, String?, Target<GlideDrawable>?, Boolean) -> Boolean)? = null
    private var onResourceReady: ((GlideDrawable?, String?, Target<GlideDrawable>?, Boolean, Boolean) -> Boolean)? = null

    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
        return onException?.invoke(e, model, target, isFirstResource) ?: false
    }

    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
        return onResourceReady?.invoke(resource, model, target, isFromMemoryCache, isFirstResource) ?: false
    }

    public fun exception(listener: ((Exception?, String?, Target<GlideDrawable>?, Boolean) -> Boolean)?) {
        onException = listener
    }

    public fun resourceReady(listener: ((GlideDrawable?, String?, Target<GlideDrawable>?, Boolean, Boolean) -> Boolean)?) {
        onResourceReady = listener
    }

}