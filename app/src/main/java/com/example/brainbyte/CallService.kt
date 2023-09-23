package com.example.brainbyte

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import retrofit2.Call
import retrofit2.Response

class CallService : Service() {
    private val CHANNEL_ID = "spam_number"
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
                CHANNEL_ID,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val number = intent?.getStringExtra("number")
        val retrofit = RetrofitClient.buildService(NumberApi::class.java)
        val result = number?.let { retrofit.getNumber(it) }
        var text:String = ""
        result?.enqueue(object : retrofit2.Callback<SpamNumberResponse>{
            override fun onResponse(call: Call<SpamNumberResponse>, response: Response<SpamNumberResponse>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    if(responseBody.status == "Success"){
                        if(responseBody.message == "Found"){
                             text = "Number is spam. Number of reports is ${responseBody.report}"
                        }
                    }
                }
            }
            override fun onFailure(call: Call<SpamNumberResponse>, t: Throwable) {

            }
        })
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Spam Number")
            .setContentText(text)
            .setSmallIcon(R.drawable.b_1)
            .build()
        startForeground(1, notification)
        return START_STICKY
    }

}