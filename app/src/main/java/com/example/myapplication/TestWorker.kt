package com.example.myapplication

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TestWorker(
    val context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        Thread.sleep(2000)

        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
    }

}
