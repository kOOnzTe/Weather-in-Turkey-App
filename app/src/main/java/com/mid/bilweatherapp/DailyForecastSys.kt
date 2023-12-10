package com.mid.bilweatherapp

import java.util.Collections


object DailyForecastSys {
    lateinit var dailyForecasts: ArrayList<DailyForecast>
        private set

    fun prepareData() {
        dailyForecasts = ArrayList()
        Collections.addAll(
            dailyForecasts,
            DailyForecast("Monday", "26/12", R.drawable.sun, "Sunny", "10°", "25°"),
            DailyForecast("Tuesday", "27/12", R.drawable.cloud, "Partly Cloudy", "9°", "22°"),
            DailyForecast("Wednesday", "28/12", R.drawable.cloud_rain, "Rainy", "8°", "20°"),
            DailyForecast("Wednesday", "28/12", R.drawable.cloud_rain, "Rainy", "8°", "20°"),
            DailyForecast("Wednesday", "28/12", R.drawable.cloud_rain, "Rainy", "8°", "20°"),
            DailyForecast("Wednesday", "28/12", R.drawable.cloud_rain, "Rainy", "8°", "20°"),
            DailyForecast("Wednesday", "28/12", R.drawable.cloud_rain, "Partly Cloudy", "8°", "20°"),
        )
    }

    fun addItem(forecast: DailyForecast) {
        dailyForecasts.add(forecast)
    }
}
