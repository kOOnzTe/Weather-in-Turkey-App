package com.mid.bilweatherapp

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.mid.bilweatherapp.db.DailyWeatherForecast
import com.mid.bilweatherapp.db.DailyWeatherViewModel
import com.mid.bilweatherapp.json.ApiClient
import com.mid.bilweatherapp.json.ApiService
import com.mid.bilweatherapp.json.WeatherResponse
import com.mid.bilweatherapp.worker.CustomWorker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit
import kotlin.math.log

object MainSys {
    lateinit var weatherService: ApiService
    lateinit var workManager: WorkManager
    lateinit var workRequest: PeriodicWorkRequest
    lateinit var dailyWeatherVM : DailyWeatherViewModel
    var currentC: Double = 0.0
    fun getWeatherData(location: String) {
        val apiKey = "87c3372fc7fd4e0cb52191243232312"
        val days = 7
        val aqi = "no"
        val alerts = "no"

        weatherService = ApiClient.getClient().create(ApiService::class.java)
        val requestWithKey = weatherService.getWeather(apiKey, location, days, aqi, alerts)

        requestWithKey.clone().enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("API Call", "Failed: ${t.message}")
            }

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    dailyWeatherVM.deleteAllForecasts()
                    val weatherResponse = response.body()
                    //Log.d("API Call", "Success: $weatherResponse")
                    // TODO: DATA WILL BE TAKEN FROM HERE INTO DATABASE
                    for (temp in weatherResponse?.forecast!!.forecastday) {
                        var dailyTemp = DailyWeatherForecast(
                            weatherResponse.location?.name.toString(),
                            temp.date.toString(),
                            temp.day?.maxtempC,
                            temp.day?.mintempC,
                            temp.day?.condition?.text,
                            temp.day?.condition?.icon,
                            temp.day?.avghumidity.toString(),
                            temp.day?.maxwindKph.toString()
                        )
                        dailyWeatherVM.addForecast(dailyTemp)
                        currentC = weatherResponse.current?.tempC!!.toDouble()
                        Log.d("DBTest", dailyTemp.condition.toString())
                    }

                } else {
                    Log.e("API Call", "Error: ${response.code()}")
                }
            }
        })

    }
    fun setWorker(context: Context) {
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        var workRequest = PeriodicWorkRequest.Builder(CustomWorker::class.java, 1, TimeUnit.HOURS).setConstraints(constraints).build();
        workManager = WorkManager.getInstance(context)
        workManager.enqueue(workRequest)
    }
}