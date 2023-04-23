package com.example.notificationfirebase

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val TAG = "MyMessagingService"
@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d(TAG, "onMessageReceived: Notification keldi")

        Log.d(TAG, "Message data payload: ${message.data}")
        if (message.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${message.data}")

            var builder = NotificationCompat.Builder(this, "2")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(message.data["sented"])
                .setContentText(message.data["body"])
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = getString(R.string.app_name)
                val descriptionText = getString(R.string.app_name)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel("2", name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                notificationManager.createNotificationChannel(channel)
            }


            notificationManager.notify(2, builder.build())
        }
    }

}