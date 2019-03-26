package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters

class TestWorker(
    val context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.getSystemService<NotificationManager>()?.apply {
                createNotificationChannel(
                    NotificationChannel(
                        "background",
                        "background",
                        NotificationManager.IMPORTANCE_LOW
                    ).apply {
                        setShowBadge(false)
                        description = "background"
                    })
            }
        }
    }

    val notificationId = 17

    protected val notificationManager by lazy { NotificationManagerCompat.from(context) }

    private val notificationBuilder by lazy {
        NotificationCompat.Builder(context, "background")
    }

    override fun doWork(): Result {
        showProgressNotification()

        Thread.sleep(3000)

        cancelProgressNotification()

        return Result.failure()
    }

    override fun onStopped() {
        super.onStopped()
    }

    private fun showProgressNotification() {
        val cancelPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            Intent(context, CancelWorkerReceiver::class.java).apply {
                action = "example.action.cancelwork"
                putExtra(INTENT_EXTRA_WORKER_UUID, id)
            },
            0
        )

        notificationManager.notify(
            notificationId, notificationBuilder
                .setContentTitle("worker")
                .setContentText("running")
                .setSmallIcon(R.drawable.ic_work_black_24dp)
                .setCategory(Notification.CATEGORY_PROGRESS)
                .addAction(
                    R.drawable.ic_cancel_black_24dp,
                    "cancel",
                    cancelPendingIntent
                )
                .build()
        )
    }

    private fun cancelProgressNotification() {
        notificationManager.cancel(notificationId)
    }
}
