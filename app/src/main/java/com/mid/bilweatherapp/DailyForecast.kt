package com.mid.bilweatherapp

class DailyForecast(var day: String,var date: String,var icon: Int, var description: String, var mostTemp: String, var leastTemp: String) {
    override fun toString(): String {
        return "Hourly Weather(day=$day, date=$date, desc:$description)"
    }
}