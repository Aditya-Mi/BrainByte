package com.example.brainbyte.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.telephony.SmsMessage
import android.util.Log

class SmsBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
//        val smsMessage:SmsMessage[] =
//        val body: StringBuilder = StringBuilder()
//        var number = ""
//        val bundle: Bundle? = intent?.extras
//        val messages: Array<SmsMessage?>
//
//        if (bundle != null) {
//            val msgObjects: Array<*>? = bundle.getString("pdus") as Array<*>?
//            messages = arrayOfNulls(msgObjects!!.size)
//            for (i in messages.indices) {
//                messages[i] = SmsMessage.createFromPdu(msgObjects[i] as ByteArray?)
//                body.append(messages[i]!!.messageBody)
//                number = messages[i]!!.originatingAddress.toString()
//                Log.e("Message","Message Body = $body")
//            }
//        }

    }
}