package com.mid.bilweatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DailyForecastRecyclerViewAdapter(
    private val context: Context,
    private val dailyWeatherList: ArrayList<DailyForecast>
) : RecyclerView.Adapter<DailyForecastRecyclerViewAdapter.DailyViewHolder>() {

    interface RecyclerAdapterInterface {
        fun displayItem(weather: DailyForecast)
    }

    private val recyclerAdapterInterface: RecyclerAdapterInterface = context as RecyclerAdapterInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.daily_weather, parent, false)
        return DailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val dailyWeather = dailyWeatherList[position]
        holder.dailyDay.text = dailyWeather.day
        holder.dailyDate.text = dailyWeather.date
        holder.dailyIcon.setImageResource(dailyWeather.icon)
        holder.dailyDesc.text = dailyWeather.description
        holder.dailyMostTemp.text = dailyWeather.mostTemp
        holder.dailyLeastTemp.text = dailyWeather.leastTemp

        holder.itemView.setOnClickListener {
            recyclerAdapterInterface.displayItem(dailyWeather)
        }
    }

    override fun getItemCount(): Int {
        return dailyWeatherList.size
    }

    inner class DailyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dailyDay: TextView = itemView.findViewById(R.id.daily_day)
        var dailyDate: TextView = itemView.findViewById(R.id.daily_date)
        var dailyIcon: ImageView = itemView.findViewById(R.id.daily_icon)
        var dailyDesc: TextView = itemView.findViewById(R.id.daily_desc)
        var dailyMostTemp: TextView = itemView.findViewById(R.id.daily_most_temp)
        var dailyLeastTemp: TextView = itemView.findViewById(R.id.daily_least_temp)
    }
}
