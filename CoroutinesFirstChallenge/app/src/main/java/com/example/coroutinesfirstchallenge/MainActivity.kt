package com.example.coroutinesfirstchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("tag","${Thread.currentThread().name}")
        }
        CoroutineScope(Dispatchers.Main).launch {
            Log.e("tag","${Thread.currentThread().name}")
        }
    }

}