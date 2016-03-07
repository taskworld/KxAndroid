package com.taskworld.kxandroid

import java.text.DecimalFormat

/**
 * Created by verachadw on 1/19/2016 AD.
 */

fun Double.format(precision: Int = 0): String {
    var formatBuilder = StringBuilder("##")
    if (precision > 0) {
        formatBuilder.append(".")
        for (i in 0..precision - 1) {
            formatBuilder.append("#")
        }
    }
    val formatter = DecimalFormat(formatBuilder.toString())
    return formatter.format(this).toString()
}

fun Double.rounded(precision: Int = 0): Double {
    return this.format(precision).toDouble()
}
