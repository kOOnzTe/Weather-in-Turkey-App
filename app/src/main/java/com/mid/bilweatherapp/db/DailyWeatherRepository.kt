package com.mid.bilweatherapp.db

import androidx.lifecycle.LiveData

/*
Used to access multiple data sources. It is used to seperate code and the architecture
 */
class DailyWeatherRepository(private val dailyWeatherDAO: DailyWeatherDAO) {
    val readAlldata:LiveData<List<DailyWeatherForecast>> = dailyWeatherDAO.getAllDailyWeatherForecasts()

    fun insertDailyWeatherForecast(dailyWeatherForecast:DailyWeatherForecast){
        dailyWeatherDAO.insertDailyWeatherForecast(dailyWeatherForecast)
    }
    /*fun insertDailyWeatherForecast(dailyWeatherForecasts:ArrayList<DailyWeatherForecast>){
        dailyWeatherDAO.insertAllDailyWeatherForecast(dailyWeatherForecasts)
    }*/

    fun updateDailyWeatherForecast(dailyWeatherForecast: DailyWeatherForecast){
        dailyWeatherDAO.updateDailyWeatherForecast(dailyWeatherForecast)
    }

    fun deleteDailyWeatherForecast(dailyWeatherForecast: DailyWeatherForecast){
        dailyWeatherDAO.deleteDailyWeatherForecast(dailyWeatherForecast)
    }

    fun deleteAllDailyWeatherForecasts(){
        dailyWeatherDAO.deleteAllDailyWeatherForecasts()
    }

    /*fun getAllDailyWeatherForecasts():LiveData<List<DailyWeatherForecast>>{
        return dailyWeatherDAO.getAllDailyWeatherForecasts()
    }*/

    fun getDailyWeatherForecast(location:String, date:String):DailyWeatherForecast{
        return dailyWeatherDAO.getDailyWeatherForecast(location, date)
    }

}