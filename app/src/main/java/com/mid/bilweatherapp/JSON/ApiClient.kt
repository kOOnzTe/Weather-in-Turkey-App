package com.mid.bilweatherapp.JSON

import com.mid.bilweatherapp.Util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.requestUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit as Retrofit
    }
}