package com.taskworld.kxandroid

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.CharacterStyle

fun StringBuilder.plus(add: String?): StringBuilder {
    if (add != null) {
        append(add)
    }
    return this
}

fun SpannableStringBuilder.append(word: String, span: CharacterStyle): SpannableStringBuilder {
    val start = length
    val end = start + word.length
    append(word)
    setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    return this
}
