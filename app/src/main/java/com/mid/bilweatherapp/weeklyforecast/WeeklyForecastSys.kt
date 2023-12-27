package com.mid.bilweatherapp.weeklyforecast

import com.mid.bilweatherapp.R
import com.mid.bilweatherapp.db.DailyWeatherForecast
import com.mid.bilweatherapp.db.DailyWeatherViewModel
import java.util.Collections

object WeeklyForecastSys {
    lateinit var weeklyForecast: ArrayList<DailyWeatherForecast>
        private set

    fun prepareData() {
        weeklyForecast = ArrayList()
        weeklyForecast.add(
            DailyWeatherForecast("Ankara", "10/20", 100.0, 20.0, "cummy", "asda", "10", "20")
        )
    }

    fun addItem(forecast: DailyWeatherForecast) {
        weeklyForecast.add(forecast)
    }

}