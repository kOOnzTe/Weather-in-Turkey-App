package com.mid.bilweatherapp.worker

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mid.bilweatherapp.MainActivity
import com.mid.bilweatherapp.MainSys


class CustomWorker(var context: Context, var workerParams: WorkerParameters):Worker (context, workerParams){
    val tagforLogcat = "WorkerEx"

    override fun doWork(): ListenableWorker.Result {
        val location: String? = getInputData().getString("location")

        return try {
            if(location != null) {
                MainSys.getWeatherData(location)
            }
            ListenableWorker.Result.success()
        } catch (throwable: Throwable) {
            Log.d(tagforLogcat, "Error Worker" + throwable.message)

            ListenableWorker.Result.failure()
        }
    }
}