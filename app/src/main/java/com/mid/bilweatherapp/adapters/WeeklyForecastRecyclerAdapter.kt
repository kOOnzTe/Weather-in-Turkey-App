package com.mid.bilweatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mid.bilweatherapp.R
import com.mid.bilweatherapp.db.DailyWeatherForecast
import com.squareup.picasso.Picasso
import com.mid.bilweatherapp.util.Constants

class WeeklyForecastRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<WeeklyForecastRecyclerAdapter.ViewHolder>() {
    var weeklyForecastList = emptyList<DailyWeatherForecast>()
    fun setData(item: List<DailyWeatherForecast>){
        weeklyForecastList = item
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weekly_weather, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = weeklyForecastList[position]

        holder.weeklyDayTitle.text = Constants.findDayOfWeek(currentItem.date)
        holder.maxCTv.text = currentItem.maxTemp.toString()
        holder.minCTv.text = currentItem.minTemp.toString()
        holder.humidityTv.text = currentItem.humidity
        holder.windTv.text = currentItem.wind_kph

        Picasso.get().load("https://"+ currentItem.icon)
            .resize(100,100)
            .centerCrop()
            .error(R.drawable.sunny)
            .into(holder.icon)

    }

    override fun getItemCount(): Int {
        return weeklyForecastList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weeklyDayTitle:TextView = itemView.findViewById(R.id.weekly_recycler_desc)
        val icon: ImageView = itemView.findViewById(R.id.morning_icon)
        val maxCTv: TextView = itemView.findViewById(R.id.maxCTv)
        val minCTv: TextView = itemView.findViewById(R.id.minCTv)
        val humidityTv: TextView = itemView.findViewById(R.id.humidity_tv)
        val windTv: TextView = itemView.findViewById(R.id.wind_tv)
    }


}