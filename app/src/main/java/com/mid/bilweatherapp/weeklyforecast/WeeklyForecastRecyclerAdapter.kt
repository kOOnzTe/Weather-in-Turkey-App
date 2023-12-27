package com.mid.bilweatherapp.weeklyforecast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mid.bilweatherapp.DailyForecast
import com.mid.bilweatherapp.R
import com.mid.bilweatherapp.db.DailyWeatherForecast

class WeeklyForecastRecyclerAdapter(
    private val context: Context,
    private val weeklyForecastList: ArrayList<DailyWeatherForecast>
) :
    RecyclerView.Adapter<WeeklyForecastRecyclerAdapter.ViewHolder>() {


    interface RecyclerAdapterInterface {
        fun displayItem(weather: DailyWeatherForecast)
    }

    private val recyclerAdapterInterface: WeeklyForecastRecyclerAdapter.RecyclerAdapterInterface =
        context as RecyclerAdapterInterface


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weekly_weather, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = weeklyForecastList[position]

        holder.weeklyDayTitle.text = currentItem.date
        holder.maxCTv.text = currentItem.maxTemp.toString()
        holder.minCTv.text = currentItem.minTemp.toString()
        holder.humidityTv.text = currentItem.humidity
        holder.windTv.text = currentItem.wind_kph

    }

    override fun getItemCount(): Int {
        return weeklyForecastList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weeklyDayTitle:TextView = itemView.findViewById(R.id.weekly_recycler_desc)
        val morningIcon: ImageView = itemView.findViewById(R.id.morning_icon)
        val maxCTv: TextView = itemView.findViewById(R.id.maxCTv)
        val minCTv: TextView = itemView.findViewById(R.id.minCTv)
        val humidityTv: TextView = itemView.findViewById(R.id.humidity_tv)
        val windTv: TextView = itemView.findViewById(R.id.wind_tv)
    }


}