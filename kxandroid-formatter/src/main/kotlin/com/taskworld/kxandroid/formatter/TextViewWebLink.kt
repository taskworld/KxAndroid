package com.taskworld.kxandroid.formatter

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

data class TextViewWebLink(val text: String, val url: String, val onClick: (widget: View?, url: String) -> Unit, val updateDrawState: (ds: TextPaint) -> Unit)

class BoldURLSpan(val url: String, val onClick: (widget: View?, url: String) -> Unit, val updateDrawState: (ds: TextPaint) -> Unit) : ClickableSpan() {

    override fun onClick(widget: View?) {
        onClick.invoke(widget, url)
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        updateDrawState.invoke(ds)
    }

}
