package com.taskworld.kxandroid.formatter

interface UnitRule {
    val pluralsId: Int
    val maxNumberInUnit: Int
    fun convertToUnit(time: Long): Int
}

abstract class TimeUnitRule : UnitRule {
    enum class TimeInMillSec(val value: Long) {
        SECOND(1000L),
        MINUTE(60 * 1000L),
        HOUR(60 * 60 * 1000L),
        DAY(24 * 60 * 60 * 1000L),
        WEEK(7 * 24 * 60 * 60 * 1000L),
        MONTH(30 * 24 * 60 * 60 * 1000L),
        YEAR(12 * 30 * 24 * 60 * 60 * 1000L);
    }

    operator fun Long.div(timeUnit: TimeInMillSec): Float {
        return this / timeUnit.value.toFloat()
    }
}

open class SecondUnitRule : TimeUnitRule() {
    override val pluralsId: Int = R.plurals.relative_time_seconds
    override val maxNumberInUnit: Int = 59

    override fun convertToUnit(time: Long): Int {
        val value = time / TimeInMillSec.SECOND
        return if (value.toInt() == 0) 1 else value.toInt()
    }
}

open class MinuteUnitRule : TimeUnitRule() {
    override val pluralsId: Int = R.plurals.relative_time_minutes
    override val maxNumberInUnit: Int = 59

    override fun convertToUnit(time: Long): Int {
        val value = time / TimeInMillSec.MINUTE
        return if (value.toInt() == 0) 1 else value.toInt()
    }
}

open class HourUnitRule : TimeUnitRule() {
    override val pluralsId: Int = R.plurals.relative_time_hours
    override val maxNumberInUnit: Int = 23

    override fun convertToUnit(time: Long): Int {
        val value = time / TimeInMillSec.HOUR
        return if (value.toInt() == 0) 1 else value.toInt()
    }
}

open class DayUnitRule : TimeUnitRule() {
    override val pluralsId: Int = R.plurals.relative_time_days
    override val maxNumberInUnit: Int = 6

    override fun convertToUnit(time: Long): Int {
        val value = time / TimeInMillSec.DAY
        return if (value.toInt() == 0) 1 else value.toInt()
    }
}

open class WeekUnitRule : TimeUnitRule() {
    override val pluralsId: Int = R.plurals.relative_time_weeks
    override val maxNumberInUnit: Int = 3

    override fun convertToUnit(time: Long): Int {
        val value = time / TimeInMillSec.WEEK
        return if (value.toInt() == 0) 1 else value.toInt()
    }
}

open class MonthUnitRule : TimeUnitRule() {
    override val maxNumberInUnit: Int = 11
    override val pluralsId: Int = R.plurals.relative_time_months

    override fun convertToUnit(time: Long): Int {
        val value = time / TimeInMillSec.MONTH
        return if (value.toInt() == 0) 1 else value.toInt()
    }
}

open class YearUnitRule : TimeUnitRule() {
    override val pluralsId: Int = R.plurals.relative_time_years
    override val maxNumberInUnit: Int = 3

    override fun convertToUnit(time: Long): Int {
        val value = time / TimeInMillSec.YEAR
        return if (value.toInt() == 0) 1 else value.toInt()
    }
}
