package com.mid.bilweatherapp.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DailyWeatherViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<DailyWeatherForecast>>
    private val repository:DailyWeatherRepository
    init {
        val dailyWeatherDAO= DailyWeatherRoomDatabase.getDatabase(application).dailyWeatherDao()
        repository = DailyWeatherRepository(dailyWeatherDAO)
        readAllData = repository.readAlldata
    }
    fun addForecast(dailyWeatherForecast:DailyWeatherForecast){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertDailyWeatherForecast(dailyWeatherForecast)
        }
    }
    /*fun addForecast(dailyWeatherForecasts: List<DailyWeatherForecast>){
        viewModelScope.launch(Dispatchers.IO) {
            dailyWeatherForecasts.forEach{
                repository.insertDailyWeatherForecast(it)
            }
        }
    } */
    /*fun deleteForecast(dailyWeatherForecast:DailyWeatherForecast){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteDailyWeatherForecast(dailyWeatherForecast)
        }
    }*/
    fun deleteAllForecasts(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllDailyWeatherForecasts()
        }
    }
    /* fun updateForecast(dailyWeatherForecast:DailyWeatherForecast){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateDailyWeatherForecast(dailyWeatherForecast)
        }
    } */
    /* fun searchForecast(location:String, date: String):DailyWeatherForecast{
            return repository.getDailyWeatherForecast(location, date)
    }*/
}