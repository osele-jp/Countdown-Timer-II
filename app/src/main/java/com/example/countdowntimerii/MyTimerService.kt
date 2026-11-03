package com.example.countdowntimerii

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MyTimerService : Service() {

    val job = Job()

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.run { getIntExtra("", 10).let { startValue ->
            startTimer(startValue)
        } }

        return super.onStartCommand(intent, flags, startId)
    }

    fun startTimer(startTime: Int){
        val scope = CoroutineScope(job + Dispatchers.Default)


        scope.launch {
            for (i in startTime downTo 0){
                Log.d("Countdown", i.toString() )
                delay(1000.milliseconds)
            }




        }
    }
}