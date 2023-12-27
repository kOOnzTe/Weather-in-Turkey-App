package com.mid.bilweatherapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mid.bilweatherapp.databinding.FragmentTopBinding
import com.mid.bilweatherapp.db.DailyWeatherForecast

class TopFragment : Fragment() {
    private lateinit var binding: FragmentTopBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun updateView(currentTemp: Double, weather: DailyWeatherForecast) {
        binding.textTemp.text = currentTemp.toInt().toString() + getString(R.string.txtTemperatureShort)
        binding.textHumidity.text = weather.humidity + "%"
        binding.textWind.text = weather.humidity + " " + getString(R.string.txtWindSpeedMeasurement)
        if(weather.condition!!.contains("rain", true)){
            binding.weatherIcon.setImageResource(R.drawable.cloudy)
        }

        Log.i("FRAGMENT","Fragment updated")
    }
}