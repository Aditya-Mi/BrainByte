package com.example.brainbyte

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import java.util.*


class NotificationHelper {
//    var CHANNEL_ID = "spam_umber"
//    var name = "Spam number"
//    var description = "Notifications for Spam Number"
//
//    fun createNotification(force: Float, speed: Float, context: Context) {
//        val notificationId = Random().nextInt()
//        val notificationBuilder: NotificationCompat.Builder = Notification.Builder(context,CHANNEL_ID)
//            .setSmallIcon(R.drawable)
//            .setContentTitle("Spam Number")
//            .setContentText(" Shake Force: $force speed= $speed")
//            .setPriority(NotificationManager.IMPORTANCE_HIGH)
//        val notificationManager =
//            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        createChannel(notificationManager, notificationBuilder)
//        notificationBuilder.setChannelId(CHANNEL_ID)
//        notificationManager.notify(notificationId, notificationBuilder.build())
//    }
//
//    fun createChannel(
//        notificationManager: NotificationManager,
//        builder: NotificationCompat.Builder?
//    ) {
//        val importance = NotificationManager.IMPORTANCE_HIGH
//        val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
//        mChannel.description = description
//        mChannel.enableLights(true)
//        mChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
//        notificationManager.createNotificationChannel(mChannel)
//    }
}