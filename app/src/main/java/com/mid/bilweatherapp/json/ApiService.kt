package com.mid.bilweatherapp.json

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // This version looks not good; thus, we changed the logic.
    //@GET("forecast.json?key=87c3372fc7fd4e0cb52191243232312&q=Ankara&days=7&aqi=no&alerts=no")
    //fun getWeather(): Call<WeatherResponse>

    @GET("forecast.json")
    fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Call<WeatherResponse>


}