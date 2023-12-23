package com.mid.bilweatherapp.JSON

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("forecast.json")
    fun getWeather(@QueryMap param: HashMap<String, String>): Call<WeatherResponse>

    /*@GET("v1/forecast.json")
    fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Call<WeatherResponse> */
}