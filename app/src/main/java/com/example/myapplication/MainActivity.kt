package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val workManager = WorkManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val work = OneTimeWorkRequestBuilder<TestWorker>().build()

        btn_start.setOnClickListener {
            workManager.beginUniqueWork("unique", ExistingWorkPolicy.KEEP, work).enqueue()
        }

        btn_cancel.setOnClickListener {
            workManager.cancelWorkById(work.id)
        }
    }
}
