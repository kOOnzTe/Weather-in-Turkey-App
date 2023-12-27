package com.mid.bilweatherapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mid.bilweatherapp.util.Constants

@Entity(tableName = "cum", primaryKeys = ["location","date"])
class DailyWeatherForecast (var location:String = "",
                            var date: String = "",
                            var maxTemp: Double? = 0.0,
                            var minTemp: Double? = 0.0,
                            var condition: String? = "",
                            var icon: String? = "",
                            var humidity: String? = "",
                            var wind_kph: String? = "")

