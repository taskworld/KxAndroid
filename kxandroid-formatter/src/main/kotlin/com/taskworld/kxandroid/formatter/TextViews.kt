package com.taskworld.kxandroid

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.taskworld.kxandroid.formatter.BoldURLSpan
import com.taskworld.kxandroid.formatter.TextViewWebLink

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
