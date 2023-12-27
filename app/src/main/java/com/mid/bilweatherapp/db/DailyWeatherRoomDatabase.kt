package com.mid.bilweatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mid.bilweatherapp.util.Constants

/*
If you change anything on the database like adding a field to table, chnaging type of a filed, deleting a filed, changing the name of the field
exportSchema: to have a version of history of your schema in your caode base, it is not required so assigned as false
 */
@Database(
    entities = [DailyWeatherForecast::class],
    version = 1,
    exportSchema = false
)
abstract class DailyWeatherRoomDatabase : RoomDatabase() {
    abstract fun dailyWeatherDao(): DailyWeatherDAO

    companion object{
        @Volatile
        private var INSTANCE:DailyWeatherRoomDatabase?=null

        fun getDatabase(context:Context):DailyWeatherRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }

            synchronized(this){
                val  instance =Room.databaseBuilder(context.applicationContext, DailyWeatherRoomDatabase::class.java, Constants.DATABASENAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
