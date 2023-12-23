package com.mid.bilweatherapp.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
it provides data to the UI and survive configuration changes. It acts as a communication center between repository and the UI
 */
class DailyWeatherViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<DailyWeatherForecast>>
    private val repository:DailyWeatherRepository
    init {
        val dailyWeatherDAO= DailyWeatherRoomDatabase.getDatabase(application).dailyWeatherDao()
        repository = DailyWeatherRepository(dailyWeatherDAO)
        readAllData = repository.readAlldata
    }
    fun addCustomer(dailyWeatherForecast:DailyWeatherForecast){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertDailyWeatherForecast(dailyWeatherForecast)
        }
    }
    fun addCustomers(dailyWeatherForecasts: List<DailyWeatherForecast>){
        viewModelScope.launch(Dispatchers.IO) { // that code will be run in background thread, coroutine scope
            dailyWeatherForecasts.forEach{
                repository.insertDailyWeatherForecast(it)
            }
        }
    }
    fun deleteCustomer(dailyWeatherForecast:DailyWeatherForecast){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteDailyWeatherForecast(dailyWeatherForecast)
        }
    }
    fun deleteAllCustomer(){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteAllDailyWeatherForecasts()
        }
    }
    fun updateCustomer(dailyWeatherForecast:DailyWeatherForecast){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.updateDailyWeatherForecast(dailyWeatherForecast)
        }
    }
    fun searchCustomer(location:String, date: String):DailyWeatherForecast{
            return repository.getDailyWeatherForecast(location, date)
    }
}