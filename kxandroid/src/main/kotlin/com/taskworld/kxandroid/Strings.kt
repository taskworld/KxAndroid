package com.taskworld.kxandroid

import java.text.SimpleDateFormat

val EMAIL_REGEX_PATTERN = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"

fun String.toStartingLetterUppercase(): String {
    if (isEmpty()) return this

    var firstChar = this[0]
    val valueOfFirstChar = firstChar.toInt()

    if (valueOfFirstChar in 97..122) {
        firstChar = (valueOfFirstChar - 32).toChar()
    }

    val builder = StringBuilder()

    var i = 0
    for (ch in this) {
        if (i == 0) {
            builder.append(firstChar)
        } else {
            builder.append(ch)
        }
        i++
    }

    return builder.toString()
}

fun String.fromSnakeToCamel(): String {
    if (this.isEmpty()) return this

    val builder = StringBuilder()

    for (token in split("_".toRegex()).toTypedArray()) {
        builder.append(token.toStartingLetterUppercase())
    }

    return builder.toString()
}

fun String.convertTimeString(oldFormat: SimpleDateFormat, newFormat: SimpleDateFormat): String {
    if (this.isEmpty()) return this
    return newFormat.format(oldFormat.parse(this))
}
