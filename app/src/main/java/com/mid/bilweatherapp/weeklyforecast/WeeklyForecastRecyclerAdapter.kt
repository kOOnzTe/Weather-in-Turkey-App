package com.mid.bilweatherapp.weeklyforecast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mid.bilweatherapp.R

class WeeklyForecastRecyclerAdapter(
    private val context: Context,
    private val weeklyForecastList: ArrayList<WeeklyForecast>
) :
    RecyclerView.Adapter<WeeklyForecastRecyclerAdapter.ViewHolder>() {


    interface RecyclerAdapterInterface {
        fun displayItem(weather: WeeklyForecast)
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

        holder.weeklyDayTitle.text = currentItem.weeklyDayTitle
        holder.morningTitle.text = currentItem.morningTitle
        holder.morningIcon.setImageResource(currentItem.morningIconResId)
        holder.morningWeatherDesc.text = currentItem.morningWeatherDesc
        holder.morningDegree.text = currentItem.morningDegree

        holder.nightTitle.text = currentItem.nightTitle
        holder.nightIcon.setImageResource(currentItem.nightIconResId)
        holder.nightWeatherDesc.text = currentItem.nightWeatherDesc
        holder.nightDegree.text = currentItem.nightDegree

        holder.itemView.setOnClickListener {
            recyclerAdapterInterface.displayItem(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return weeklyForecastList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weeklyDayTitle:TextView = itemView.findViewById(R.id.weekly_recycler_desc)
        val morningTitle: TextView = itemView.findViewById(R.id.morning_title)
        val morningIcon: ImageView = itemView.findViewById(R.id.morning_icon)
        val morningWeatherDesc: TextView = itemView.findViewById(R.id.morning_weather_desc)
        val morningDegree: TextView = itemView.findViewById(R.id.morning_degree)
        val nightTitle: TextView = itemView.findViewById(R.id.night_title)
        val nightIcon: ImageView = itemView.findViewById(R.id.night_icon)
        val nightWeatherDesc: TextView = itemView.findViewById(R.id.night_weather_desc)
        val nightDegree: TextView = itemView.findViewById(R.id.night_degree)
    }


}