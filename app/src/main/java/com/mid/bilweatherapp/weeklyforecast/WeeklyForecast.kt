package com.mid.bilweatherapp.weeklyforecast

data class WeeklyForecast(
    val weeklyDayTitle:String,
    val morningTitle: String,
    val morningIconResId: Int,
    val morningWeatherDesc: String,
    val morningDegree: String,
    val nightTitle: String,
    val nightIconResId: Int,
    val nightWeatherDesc: String,
    val nightDegree: String
){
    override fun toString(): String {
        return "Hourly Weather(day=$morningTitle, date=$nightTitle)"
    }
}