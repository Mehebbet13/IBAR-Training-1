package com.example.workmanagerdemoone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY_COUNT = "key_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            setPeriodicWorkRequest()
        }
    }

    private fun setOneTimeWorkRequest() {
        val workManager = WorkManager.getInstance(applicationContext)

        val data: Data = Data.Builder()
            .putInt(KEY_COUNT, 125)
            .build()
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()

        val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()

        val downloadingRequest = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()
        val parallelWorks = mutableListOf<OneTimeWorkRequest>()
        parallelWorks.add(downloadingRequest)
        parallelWorks.add(filteringRequest)

        workManager
            .beginWith(parallelWorks)
            .then(compressingRequest)
            .then(oneTimeWorkRequest)
            .enqueue()
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, Observer {
                textView.text = it.state.name

                if (it.state.isFinished) {
                    val data = it.outputData
                    val message = data.getString(UploadWorker.KEY_WORKER)
                    Log.e("mytag", message.toString())
                }
            })

    }

    private fun setPeriodicWorkRequest(){
        val perriodicWorkRequest = PeriodicWorkRequest
            .Builder(DownloadingWorker::class.java,16,TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(perriodicWorkRequest)
    }
}