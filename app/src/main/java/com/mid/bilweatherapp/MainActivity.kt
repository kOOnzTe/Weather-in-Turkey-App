package com.mid.bilweatherapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mid.bilweatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DailyForecastRecyclerViewAdapter.RecyclerAdapterInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DailyForecastRecyclerViewAdapter
    private var gestureDetector: GestureDetectorCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Hiding title bar
        supportActionBar?.hide()
        setContentView(binding.root)

        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

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


