package com.mid.bilweatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mid.bilweatherapp.json.ApiService
import com.mid.bilweatherapp.json.WeatherResponse
import com.mid.bilweatherapp.databinding.ActivityMainBinding
import com.mid.bilweatherapp.json.ApiClient
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback
import android.media.MediaPlayer
import androidx.core.content.ContentProviderCompat.requireContext

class MainActivity : AppCompatActivity(), DailyForecastRecyclerViewAdapter.RecyclerAdapterInterface {

    lateinit var weatherService: ApiService
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DailyForecastRecyclerViewAdapter
    private var gestureDetector: GestureDetectorCompat? = null
    private lateinit var mediaPlayer: MediaPlayer
    @SuppressLint("ClickableViewAccessibility") // to remove gestureDetector warning
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Hiding title bar
        supportActionBar?.hide()
        setContentView(binding.root)

        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)


        // Initial JSON Requests
        getWeatherData("Ankara")
        getWeatherData("Istanbul")
        getWeatherData("Izmir")
        // TODO: Burada tek bir initial request kalsın mesela Ankara olan (açılışta ekranda gözüksün diye), sonrasında userdan (mesela textview inputundan) şehir ismi alıp bu fonksiyonun parametresine koyulacak, fonksiyon json request atacak. ama bu user input işi büyük ihtimalle onClick'te falan olmalı onCreate yerine.

        // Sound
        mediaPlayer = MediaPlayer.create(this, R.raw.weathermusic)
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.start()
            mediaPlayer.isLooping = true
        }

        // Prepare the data for the RecyclerView
        DailyForecastSys.prepareData()

        // Set up the RecyclerView
        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerSocial.layoutManager = layoutManager

        // Fill the RecyclerView with the adapter
        adapter = DailyForecastRecyclerViewAdapter(this, DailyForecastSys.dailyForecasts)
        binding.recyclerSocial.adapter = adapter

        // Combined GestureDetector for both "long-press" and "double-tap" Gestures
        gestureDetector = GestureDetectorCompat(this, CustomGesture())

        // Set onTouchListener to handle "long-press" and "double-tap"  gestures
        binding.root.setOnTouchListener { _, event ->
            gestureDetector?.onTouchEvent(event)
            true
        }



    }

    override fun displayItem(weather: DailyForecast) {

    }

    private fun getWeatherData(location: String) {
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
                    val weatherResponse = response.body()
                    Log.d("API Call", "Success: $weatherResponse")
                    // TODO: DATA WILL BE TAKEN FROM HERE INTO DATABASE
                } else {
                    Log.e("API Call", "Error: ${response.code()}")
                }
            }
        })
    }

    inner class CustomGesture : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean { // due to prevent 2 times Snackbar, we used onDoubleTap instead of onDoubleTapEvent
            Snackbar.make(binding.root, "Double tap gesture detected on the weather app!", Snackbar.LENGTH_SHORT).show()
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Snackbar.make(binding.root, "Long press gesture detected on the weather app!", Snackbar.LENGTH_SHORT).show()
        }
    }
}


