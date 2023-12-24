package com.mid.bilweatherapp.weeklyforecast

import com.mid.bilweatherapp.R
import java.util.Collections

object WeeklyForecastSys {
    lateinit var weeklyForecast: ArrayList<WeeklyForecast>
        private set

    fun prepareData() {
        weeklyForecast = ArrayList()
        Collections.addAll(
            weeklyForecast,
            WeeklyForecast("Monday 26/11","Morning", R.drawable.sun, "Sunny", "23°", "Night", R.drawable.cloud, "Partly Cloudy", "18°"),
            WeeklyForecast("Tuesday 26/11","Morning", R.drawable.sun, "Sunny", "23°", "Night", R.drawable.cloud, "Partly Cloudy", "18°"),
            WeeklyForecast("Wednesday 26/11","Morning", R.drawable.sun, "Sunny", "23°", "Night", R.drawable.cloud, "Partly Cloudy", "18°"),
            WeeklyForecast("Thursday 26/11","Morning", R.drawable.sun, "Sunny", "23°", "Night", R.drawable.cloud, "Partly Cloudy", "18°"),
            WeeklyForecast("Friday 26/11","Morning", R.drawable.sun, "Sunny", "23°", "Night", R.drawable.cloud, "Partly Cloudy", "18°"),
            WeeklyForecast("Saturday 26/11","Morning", R.drawable.sun, "Sunny", "23°", "Night", R.drawable.cloud, "Partly Cloudy", "18°"),
            WeeklyForecast("Sunday 26/11","Morning", R.drawable.sun, "Sunny", "23°", "Night", R.drawable.cloud, "Partly Cloudy", "18°"),
        )
    }

    fun addItem(forecast: WeeklyForecast) {
        weeklyForecast.add(forecast)
    }

}