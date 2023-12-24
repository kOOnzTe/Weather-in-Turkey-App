package com.mid.bilweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mid.bilweatherapp.databinding.ActivityWeeklyForecastBinding
import com.mid.bilweatherapp.weeklyforecast.WeeklyForecast
import com.mid.bilweatherapp.weeklyforecast.WeeklyForecastRecyclerAdapter
import com.mid.bilweatherapp.weeklyforecast.WeeklyForecastSys

class WeeklyForecastActivity : AppCompatActivity(), WeeklyForecastRecyclerAdapter.RecyclerAdapterInterface {
    private lateinit var weeklyForecastAdapter: WeeklyForecastRecyclerAdapter
    private lateinit var binding: ActivityWeeklyForecastBinding
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeeklyForecastBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        try {
            WeeklyForecastSys.prepareData()

            layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.recyclerWeeklyForecast.layoutManager = layoutManager

            weeklyForecastAdapter = WeeklyForecastRecyclerAdapter(this, WeeklyForecastSys.weeklyForecast)
            binding.recyclerWeeklyForecast.adapter = weeklyForecastAdapter
        } catch (e: Exception) {
            Log.e("WeeklyForecastActivity", "Error in setting up RecyclerView", e)
            // Optionally, show a user-friendly error message
            Toast.makeText(this, "Error setting up the forecast view", Toast.LENGTH_LONG).show()
        }

    }

    override fun displayItem(weather: WeeklyForecast) {
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
    }
}