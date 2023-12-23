package com.mid.bilweatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ctis487.roomdatabaseex2.util.Constants
import com.mid.bilweatherapp.db.DailyWeatherForecast

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
        @Volatile  //it makes that instance to visible to other threads
        private var INSTANCE:DailyWeatherRoomDatabase?=null

        fun getDatabase(context:Context):DailyWeatherRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }
            /*
            everthing in this block protected from concurrent execution by multiple threads.In this block database instance is created
            same database instance will be used. If many instance are used, it will be so expensive
             */
            synchronized(this){
                val  instance =Room.databaseBuilder(context.applicationContext, DailyWeatherRoomDatabase::class.java, Constants.DATABASENAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
