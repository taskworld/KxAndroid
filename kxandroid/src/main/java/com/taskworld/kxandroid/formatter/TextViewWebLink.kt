package com.taskworld.kxandroid.util.formatter

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

/**
 * Created by Johnny Dew on 2/2/16.
 */

public data class TextViewWebLink(val text: String, val url: String, val onClick: (widget: View?, url: String) -> Unit, val updateDrawState: (ds: TextPaint) -> Unit)

public class BoldURLSpan(val url: String, val onClick: (widget: View?, url: String) -> Unit, val updateDrawState: (ds: TextPaint) -> Unit) : ClickableSpan() {

    override fun onClick(widget: View?) {
        onClick.invoke(widget, url)
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        updateDrawState.invoke(ds)
    }

}
