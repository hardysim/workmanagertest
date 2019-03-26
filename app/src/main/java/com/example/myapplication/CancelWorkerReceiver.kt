package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.WorkManager
import java.util.*

const val INTENT_EXTRA_WORKER_UUID = "INTENT_EXTRA_WORKER_UUID"

class CancelWorkerReceiver : BroadcastReceiver() {

    // region BroadcastReceiver

    override fun onReceive(context: Context?, intent: Intent?) {
        val uuid = intent?.getSerializableExtra(INTENT_EXTRA_WORKER_UUID) as? UUID ?: return
        WorkManager.getInstance().cancelWorkById(uuid)
    }

    // endregion
}
