package com.mid.bilweatherapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mid.bilweatherapp.json.ApiService
import com.mid.bilweatherapp.json.WeatherResponse
import com.mid.bilweatherapp.databinding.ActivityMainBinding
import com.mid.bilweatherapp.json.ApiClient
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback
import android.media.MediaPlayer
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mid.bilweatherapp.db.DailyWeatherForecast
import com.mid.bilweatherapp.db.DailyWeatherViewModel

class MainActivity : FragmentActivity(), DailyForecastRecyclerViewAdapter.RecyclerAdapterInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var dailyForecastAdapter: DailyForecastRecyclerViewAdapter
    private lateinit var dailyWeatherVM : DailyWeatherViewModel
    private lateinit var currentCity: String

    private var gestureDetector: GestureDetectorCompat? = null
    private lateinit var mediaPlayer: MediaPlayer

    lateinit var fragment : TopFragment
    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    @SuppressLint("ClickableViewAccessibility") // to remove gestureDetector warning
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Hiding title bar
        //supportActionBar?.hide()
        setContentView(binding.root)

        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        dailyWeatherVM = ViewModelProvider(this).get(DailyWeatherViewModel::class.java)
        getData()
        MainSys.dailyWeatherVM = dailyWeatherVM

        //initialize fragment
        fragment = TopFragment()
        loadFrag(fragment)

        //sets worker
        MainSys.setWorker(this)

        // Initial JSON Requests
        currentCity = binding.citySpinner.selectedItem.toString()
        MainSys.getWeatherData(currentCity)
        // TODO: Burada tek bir initial request kalsın mesela Ankara olan (açılışta ekranda gözüksün diye), sonrasında userdan (mesela textview inputundan) şehir ismi alıp bu fonksiyonun parametresine koyulacak, fonksiyon json request atacak. ama bu user input işi büyük ihtimalle onClick'te falan olmalı onCreate yerine.

        // Sound
        mediaPlayer = MediaPlayer.create(this, R.raw.weathermusic)
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.start()
            mediaPlayer.isLooping = true
        }

        // Prepare the data for the RecyclerView
        //DailyForecastSys.prepareData()

        // Set up the RecyclerView
        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerSocial.layoutManager = layoutManager

        // Fill the RecyclerView with the adapter
        dailyForecastAdapter = DailyForecastRecyclerViewAdapter(this)
        binding.recyclerSocial.adapter = dailyForecastAdapter

        // Combined GestureDetector for both "long-press" and "double-tap" Gestures
        gestureDetector = GestureDetectorCompat(this, CustomGesture())

        // Set onTouchListener to handle "long-press" and "double-tap"  gestures
        binding.root.setOnTouchListener { _, event ->
            gestureDetector?.onTouchEvent(event)
            true
        }

        binding.weeklySeeMore.setOnClickListener {
            try {
                val intent = Intent(this, WeeklyForecastActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun displayItem(weather: DailyWeatherForecast) {

    }

    inner class CustomGesture : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean { // due to prevent 2 times Snackbar, we used onDoubleTap instead of onDoubleTapEvent
            Snackbar.make(binding.root, "Refreshing the list!", Snackbar.LENGTH_SHORT).show()
            MainSys.getWeatherData(currentCity)
            fragment.updateView("Cloudy", 23.0, "34", "99")
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Snackbar.make(binding.root, "Long press gesture detected on the weather app!", Snackbar.LENGTH_SHORT).show()
        }
    }
    fun loadFrag(dynamicFragment: Fragment) {
        val bundle = Bundle()
        //put here the related information for printing
        bundle.putInt("num1", 10)
        bundle.putString("num2", "20")
        dynamicFragment.arguments = bundle
        fm = supportFragmentManager
        ft = fm.beginTransaction()
        ft.replace(R.id.frTop, dynamicFragment)
        ft.commit()
    }

    fun getData() {
        //Whenever data is changed that change will refresh the recyclerview
        dailyWeatherVM.readAllData.observe(this, Observer { customers ->
            dailyForecastAdapter.setData(customers)
        })
    }
}


