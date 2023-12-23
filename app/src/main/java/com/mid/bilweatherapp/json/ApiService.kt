package com.mid.bilweatherapp.json

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("forecast.json?key=87c3372fc7fd4e0cb52191243232312&q=Ankara&days=7&aqi=no&alerts=no")
    fun getWeather(/*@QueryMap param: HashMap<String, String>*/): Call<WeatherResponse>

    /*@GET("v1/forecast.json")
    fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Call<WeatherResponse> */
}