package com.mid.bilweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mid.bilweatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),  DailyForecastRecyclerViewAdapter.RecyclerAdapterInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DailyForecastRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Prepare the data for the RecyclerView
        DailyForecastSys.prepareData()

        // Set up the RecyclerView
        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerSocial.layoutManager = layoutManager

        // Fill the RecyclerView with the adapter
        adapter = DailyForecastRecyclerViewAdapter(this, DailyForecastSys.dailyForecasts)
        binding.recyclerSocial.adapter = adapter
    }

    override fun displayItem(weather: DailyForecast) {

    }
}
