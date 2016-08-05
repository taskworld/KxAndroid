package com.taskworld.kxandroid

import java.text.DecimalFormat

fun Double.format(precision: Int = 1): String {
    val formatBuilder = StringBuilder("##")
    if (precision > 0) {
        formatBuilder.append(".")
        for (i in 0..precision - 1) {
            formatBuilder.append("#")
        }
    }
    val formatter = DecimalFormat(formatBuilder.toString())
    return formatter.format(this).toString()
}

fun Float.format(precision: Int = 1) = this.toDouble().format(precision)

fun Double.rounded(precision: Int = 1): Double {
    return this.format(precision).toDouble()
}

