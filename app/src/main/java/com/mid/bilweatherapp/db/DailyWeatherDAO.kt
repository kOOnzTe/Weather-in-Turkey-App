package com.mid.bilweatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ctis487.roomdatabaseex2.util.Constants
import com.mid.bilweatherapp.db.DailyWeatherForecast
import kotlinx.coroutines.flow.Flow


//Data Access Object: It contains all the methods used for accessing to the database. Inside it all the required queries will be created
@Dao
interface DailyWeatherDAO {
    // The conflict strategy defines what happens,if there is an existing entry.
    // The default action is ABORT.
    //@Insert(onConflict = OnConflictStrategy.IGNORE) //if there is a conflict it will be ignored, if there is a new customer with the same data it will jut ignored
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeatherForecast(customer: DailyWeatherForecast) // suspend is written because it will be used with coroutine

    @Update
    fun updateDailyWeatherForecast(customer: DailyWeatherForecast)

    @Delete
    fun deleteDailyWeatherForecast(customer: DailyWeatherForecast)

    @Query("DELETE FROM ${Constants.TABLENAME}")
    fun deleteAllDailyWeatherForecasts()

    @Query("SELECT * FROM ${Constants.TABLENAME} ORDER BY id ASC")
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