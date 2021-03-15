package com.example.workmanagerdemoone


import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DownloadingWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        try {
            for (i in 0 until 3000) {
                Log.e("mytag DownloadingWorker", i.toString())
            }
            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate: String = time.format(Date())
            Log.e("mytag DownloadingWorker", currentDate )

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}