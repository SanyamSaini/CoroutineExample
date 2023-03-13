package com.coroutine.example

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var tvHello: TextView

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        tvHello = findViewById(R.id.tvHello)

        GlobalScope.launch(Dispatchers.Main) {
            execute()
        }
    }

    private suspend fun execute() {
        Log.d("SANYAM", "Before")
        withContext(Dispatchers.IO) {
            delay(1000)
            Log.d("SANYAM", "Inside")
        }

        Log.d("SANYAM", "After")
    }

}