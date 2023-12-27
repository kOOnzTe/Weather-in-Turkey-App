package com.mid.bilweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mid.bilweatherapp.databinding.ActivityWeeklyForecastBinding
import com.mid.bilweatherapp.db.DailyWeatherForecast
import com.mid.bilweatherapp.adapters.WeeklyForecastRecyclerAdapter
import com.mid.bilweatherapp.db.DailyWeatherViewModel

class WeeklyForecastActivity : AppCompatActivity(), WeeklyForecastRecyclerAdapter.RecyclerAdapterInterface {
    private lateinit var weeklyForecastAdapter: WeeklyForecastRecyclerAdapter
    private lateinit var binding: ActivityWeeklyForecastBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var dailyWeatherViewModel: DailyWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeeklyForecastBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        dailyWeatherViewModel = ViewModelProvider(this).get(DailyWeatherViewModel::class.java)
        getData()

        try {
            layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.recyclerWeeklyForecast.layoutManager = layoutManager

            weeklyForecastAdapter = WeeklyForecastRecyclerAdapter(this)
            binding.recyclerWeeklyForecast.adapter = weeklyForecastAdapter
        } catch (e: Exception) {
            Log.e("WeeklyForecastActivity", "Error in setting up RecyclerView", e)
            // Optionally, show a user-friendly error message
            Toast.makeText(this, "Error setting up the forecast view", Toast.LENGTH_LONG).show()
        }

        binding.btnGoBack.setOnClickListener {
            finish()
        }

    }

    override fun displayItem(weather: DailyWeatherForecast) {
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
    }

    fun getData() {
        //Whenever data is changed that change will refresh the recyclerview
        dailyWeatherViewModel.readAllData.observe(this, Observer { customers ->
            weeklyForecastAdapter.setData(customers)
        })
    }
}