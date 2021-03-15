package com.example.workmanagerdemoone

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class CompressingWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        try {
            for (i in 0 until 300) {
                Log.e("mytag", i.toString())
            }
            return Result.success()
        }
        catch (e:Exception){
            return Result.failure()
        }
    }
}