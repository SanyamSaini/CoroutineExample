package com.coroutine.example

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var tvHello: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvHello = findViewById(R.id.tvHello)

//        tvHello.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                Log.d("SANYAM : ", "1 - ${Thread.currentThread().name}")
//            }
//
//            GlobalScope.launch(Dispatchers.Main) {
//                Log.d("SANYAM : ", "2 - ${Thread.currentThread().name}")
//            }
//
//            MainScope().launch(Dispatchers.Default) {
//                Log.d("SANYAM : ", "3 - ${Thread.currentThread().name}")
//            }
//        }

        CoroutineScope(Dispatchers.IO).launch {
//            task1()
            printFollowersLaunch()
        }

        CoroutineScope(Dispatchers.IO).launch {
//            task1()
            printFollowersAsync()
        }

//        CoroutineScope(Dispatchers.IO).launch {
//            task2()
//        }


    }

    private suspend fun printFollowersLaunch() {
        var fbFollowers = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            fbFollowers = getFbFollowers()
        }

        job.join()
        Log.d("SANYAM", fbFollowers.toString())
    }

    private suspend fun printFollowersAsync() {
        val job = CoroutineScope(Dispatchers.IO).async {
            getFbFollowers()
        }
        Log.d("SANYAM", job.await().toString())
    }

    private suspend fun getFbFollowers(): Int {
        delay(1000)
        return 54
    }

    suspend fun task1() {
        Log.d("SANYAM", "Starting task 1")
        delay(1000)
        Log.d("SANYAM", "Ending task 1")
    }

    suspend fun task2() {
        Log.d("SANYAM", "Starting task 2")
        delay(1000)
        Log.d("SANYAM", "Ending task 2")
    }
}