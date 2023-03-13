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

        GlobalScope.launch(Dispatchers.Main) {
            execute()
        }
    }

    private suspend fun execute() {
//        val parentJob = GlobalScope.launch(Dispatchers.Main) {
//            Log.d("SANYAM", "Parent Job Started")
//
//            val childJob = launch {
//                Log.d("SANYAM", "Child Job Started")
//                delay(5000)
//                Log.d("SANYAM", "Child Job Ended")
//            }
//
//            delay(3000)
//            childJob.cancel()
//            Log.d("SANYAM", "Parent Job Ended")
//        }
//
////        delay(1000)
////        parentJob.cancel()
//        parentJob.join()
//        Log.d("SANYAM", "Parent Job Completed")

        val parentJob = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..1000) {
                if (isActive) {
                    executeLongRunningTask()
                    Log.d("SANYAM", i.toString())
                }
            }
        }

        delay(100)
        Log.d("SANYAM", "Cancelling Job")
        parentJob.cancel()
        parentJob.join()
        Log.d("SANYAM", "Job completed")


    }

    private fun executeLongRunningTask() {
        for (i in 0..100000000) {

        }
    }

}