package com.mid.bilweatherapp.util

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import java.util.Locale

object Constants {

    const val REQUESTURL: String = "https://api.weatherapi.com/v1/"
    const val DATABASENAME = "forecast"
    const val TABLENAME = "forecast"

    // Conversion function
    fun findDayOfWeek(dateString: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date = sdf.parse(dateString)

        val calendar = Calendar.getInstance()
        calendar.time = date

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val days = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        return days[dayOfWeek - 1]
    }
}