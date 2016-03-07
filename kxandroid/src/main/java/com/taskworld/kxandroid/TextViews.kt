package com.taskworld.kxandroid

import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.taskworld.kxandroid.util.formatter.BoldURLSpan
import com.taskworld.kxandroid.util.formatter.TextViewWebLink

/**
 * Created by VerachadW on 1/28/15.
 */

private var _textAppearance: Int = 0
var TextView.textAppearance: Int
    get() {
        return _textAppearance
    }
    set(value) {
        if (android.os.Build.VERSION.SDK_INT < 23) {
            setTextAppearance(context, value)
        } else {
            setTextAppearance(value)
        }
        _textAppearance = value
    }

fun TextView.onTextChangedListener(init: _TextView_TextWatcher.() -> Unit) {
    val listener = _TextView_TextWatcher()
    listener.init()
    addTextChangedListener(listener)
}

//private stuffs
class _TextView_TextWatcher : TextWatcher {

    private var beforeTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null
    private var onTextChanged: ((CharSequence, Int, Int, Int) -> Unit)? = null
    private var afterTextChanged: ((Editable?) -> Unit)? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChanged?.invoke(s, start, count, after)
    }

    //proxy method
    fun beforeTextChanged(listener: (CharSequence?, Int, Int, Int) -> Unit) {
        beforeTextChanged = listener
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onTextChanged?.invoke(s, start, before, count)
    }

    //proxy method
    fun onTextChanged(listener: (CharSequence, Int, Int, Int) -> Unit) {
        onTextChanged = listener
    }

    override fun afterTextChanged(editable: Editable?) {
        afterTextChanged?.invoke(editable)
    }

    //proxy method
    fun afterTextChanged(listener: (Editable?) -> Unit) {
        afterTextChanged = listener
    }

}

fun TextView.bindWebLink(displayText: String, vararg words: TextViewWebLink) {
    val sb: SpannableStringBuilder = SpannableStringBuilder(displayText)
    words.forEach {
        val start = displayText.indexOf(it.text)
        val end = start + it.text.length

        if (start >= 0) {
            val span = BoldURLSpan(it.url, it.onClick, it.updateDrawState)
            sb.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        }
    }

    text = sb
    movementMethod = LinkMovementMethod.getInstance()
}
