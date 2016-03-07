package com.taskworld.kxandroid.util.formatter

import com.taskworld.kxandroid.extension.rounded


/**
 * Created by tipatai on 11/20/15 AD.
 */

val BYTE_UNIT_VALUE = 1024.0

enum class ByteUnit(open val unitString: String) {
    KILO_BYTE("KB"),
    MEGA_BYTE("MB"),
    GIGA_BYTE("GB");

    val divider: Double
        get() = Math.pow(BYTE_UNIT_VALUE, (this.ordinal + 1).toDouble())
}

fun Int.toFileSizeString(): String {
    return this.toDouble().toFileSizeString()
}

fun Double.toFileSizeString(): String {
    val (converted, unit) = convertToFileSizeFormat(this)
    return "${converted.rounded(2)} ${unit.unitString}"
}

private fun convertToFileSizeFormat(value: Double): Pair<Double, ByteUnit> {
    var convertedValue = 0.0
    val unit = ByteUnit.values().reversed().find {
        convertedValue = value / it.divider
        convertedValue > 1.0
    } ?: ByteUnit.values().last()

    return Pair(convertedValue, unit)
}




