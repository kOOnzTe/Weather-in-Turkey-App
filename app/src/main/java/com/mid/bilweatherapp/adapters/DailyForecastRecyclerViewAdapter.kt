package com.mid.bilweatherapp

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mid.bilweatherapp.db.DailyWeatherForecast
import com.squareup.picasso.Picasso
import com.mid.bilweatherapp.util.Constants
import java.util.Locale

class DailyForecastRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<DailyForecastRecyclerViewAdapter.DailyViewHolder>() {
    var dailyWeatherList = emptyList<DailyWeatherForecast>()
    fun setData(item: List<DailyWeatherForecast>){
        dailyWeatherList = item
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.daily_weather, parent, false)
        return DailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val dailyWeather = dailyWeatherList[position]
        holder.dailyDate.text = Constants.findDayOfWeek(dailyWeather.date)

        Picasso.get().load("https://"+dailyWeather.icon)
            .resize(100,100)
            .centerCrop()
            .error(R.drawable.sunny)
            .into(holder.dailyIcon)

        holder.dailyDesc.text = dailyWeather.condition
        holder.dailyMostTemp.text = dailyWeather.maxTemp?.toInt().toString()
        holder.dailyLeastTemp.text = dailyWeather.minTemp?.toInt().toString()

    }

    override fun getItemCount(): Int {
        return dailyWeatherList.size
    }

    inner class DailyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dailyDate: TextView = itemView.findViewById(R.id.daily_date)
        var dailyIcon: ImageView = itemView.findViewById(R.id.daily_icon)
        var dailyDesc: TextView = itemView.findViewById(R.id.daily_desc)
        var dailyMostTemp: TextView = itemView.findViewById(R.id.daily_most_temp)
        var dailyLeastTemp: TextView = itemView.findViewById(R.id.daily_least_temp)
    }

    fun findDayOfWeek(dateString: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date = sdf.parse(dateString)

        val calendar = Calendar.getInstance()
        calendar.time = date

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val days = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        return days[dayOfWeek - 1]
    }
}
