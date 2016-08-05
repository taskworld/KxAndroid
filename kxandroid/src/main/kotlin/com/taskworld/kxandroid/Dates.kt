package com.taskworld.kxandroid

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

val millisecondInADay = 86400000

fun Date.dayDiff(date: Date): Long {
    val timeDiff = Math.abs(time - date.time)
    return if (timeDiff % millisecondInADay > 0) {
        (timeDiff / millisecondInADay) + 1
    } else {
        timeDiff / millisecondInADay        // this is rare, remainder must be zero.
    }
}

fun Date.isSameDay(date: Date): Boolean {
    val cal1 = Calendar.getInstance()
    val cal2 = Calendar.getInstance()
    cal1.time = this
    cal2.time = date
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
            && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

val Date.midnight: Date
    get() {
        val cal = Calendar.getInstance().apply {
            time = this@midnight
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            time
        }
        return cal.time
    }

/**
 * Return date by device offset from UTC. Make sure the com.itorama.taskworld.receiver Date is in UTC.
 * This is implemented for specific use case. Because it is not a proper way to deal with timezone since Date object represents time for all timezones and DateFormatter will handle timezone display.
 * In TW-App, this is used to group data base on device timezone date.
 */
val Date.deviceTime: Date
    get() {
        val formatter = SimpleDateFormat()
        // parse to String using device timezone.
        val offsetTimeString = formatter.format(this)
        // parse result String back to Date using device timezone.
        return formatter.parse(offsetTimeString)
    }