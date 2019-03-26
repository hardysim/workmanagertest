package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            WorkManager.getInstance().beginUniqueWork(
                "unique",
                ExistingWorkPolicy.KEEP,
                OneTimeWorkRequestBuilder<TestWorker>().build()
            ).enqueue()
        }
    }
}
