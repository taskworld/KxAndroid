package com.taskworld.kxandroid.util.formatter

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.properties.Delegates

/**
 * Created by VerachadW on 6/19/15.
 */

class RelativeTimeFormatter {

    lateinit private var appContext: Context

    constructor(context: Context) {
        appContext = context.applicationContext
    }

    companion object {
        @Deprecated("This method is unnecessary in M14 or later, we should move to use apply instead", ReplaceWith("apply")) fun createWithOptions(context: Context, init: RelativeTimeFormatter.() -> Unit): RelativeTimeFormatter {
            val instance = RelativeTimeFormatter(context)
            instance.init()
            return instance
        }
    }

    var pastExpression: String = "%s ago"
    var presentExpression: String = "Just now"
    var futureExpression: String = "In %s"

    var rules: List<UnitRule> by Delegates.observable(listOf<UnitRule>(SecondUnitRule(), MinuteUnitRule(),
            HourUnitRule(), DayUnitRule(), WeekUnitRule(), MonthUnitRule(), YearUnitRule())) { meta, old, new ->
        if (new.isEmpty()) {
            throw IllegalArgumentException("The list should contain at least 1 UnitRule")
        }
    }

    var customDateTimeFormat: String? = null

    fun format(dateTime: Date): String = formatRelative(System.currentTimeMillis(), dateTime.time)

    fun format(dateTimeInMills: Long): String = formatRelative(System.currentTimeMillis(), dateTimeInMills)

    private fun formatRelative(startTime: Long, targetTime: Long): String {

        val timeDiff = targetTime - startTime

        if (timeDiff < 0L) {
            return formatTime(targetTime, Math.abs(timeDiff), pastExpression)
        } else if (timeDiff == 0L) {
            return presentExpression
        } else {
            return formatTime(targetTime, timeDiff, futureExpression)
        }
    }

    private fun formatTime(targetTime: Long, timeDiff: Long, expression: String): String {

        for (rule in rules) {
            var string = applyTimeRule(expression, timeDiff, rule, false)
            if (string.isNotBlank()) return string
        }

        // If no rule is matched, format the time with SimpleDateFormat or use the last rule to apply
        if (customDateTimeFormat != null) {
            val formatter = SimpleDateFormat(customDateTimeFormat)
            return formatter.format(Date(targetTime))
        } else {
            return applyTimeRule(expression, timeDiff, rules.last(), true)
        }
    }

    private fun applyTimeRule(expression: String, timeDiff: Long, rule: UnitRule, ignoreMax: Boolean): String {
        val timeInUnit = rule.convertToUnit(timeDiff)

        if (!ignoreMax && timeInUnit >= rule.maxNumberInUnit) {
            return ""
        } else {
            // Since Kotlin.String still not support format() method, we have to use java.lang.String for now.
            return java.lang.String.format(expression,
                    appContext.resources.getQuantityString(rule.pluralsId, timeInUnit,
                            timeInUnit))
        }
    }

}

