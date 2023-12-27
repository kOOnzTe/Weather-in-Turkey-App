package com.mid.bilweatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mid.bilweatherapp.util.Constants


@Dao
interface DailyWeatherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeatherForecast(customer: DailyWeatherForecast)

    @Update
    fun updateDailyWeatherForecast(customer: DailyWeatherForecast)

    @Delete
    fun deleteDailyWeatherForecast(customer: DailyWeatherForecast)

    @Query("DELETE FROM ${Constants.TABLENAME}")
    fun deleteAllDailyWeatherForecasts()

    @Query("SELECT * FROM ${Constants.TABLENAME} ORDER BY date ASC")
    fun getAllDailyWeatherForecasts():LiveData<List<DailyWeatherForecast>>

    @Query("SELECT * FROM ${Constants.TABLENAME} WHERE location = :location and date = :date ")
    fun getDailyWeatherForecast(location: String, date: String):DailyWeatherForecast

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDailyWeatherForecast(customers: ArrayList<DailyWeatherForecast>){
        customers.forEach{
            insertDailyWeatherForecast(it)
        }
    }

}