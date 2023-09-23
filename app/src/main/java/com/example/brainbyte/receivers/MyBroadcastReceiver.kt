package com.example.brainbyte.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.CallLog
import android.telephony.TelephonyManager
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.brainbyte.CallService


class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
//        val state = intent?.extras?.getString(TelephonyManager.EXTRA_STATE)
//        if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
//            val number = getNumber(context)
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
//                val request: OneTimeWorkRequest =
//                    Builder(BackupWorker::class.java).addTag("BACKUP_WORKER_TAG").build()
//                WorkManager.getInstance(context).enqueue(request)
//            }else{
//                val i = Intent(context,CallService::class.java)
//                i.putExtra("number",number)
//                context?.startForegroundService(i)
//            }
//        }
    }
    private fun getNumber(context: Context?):String?{
        val resolver = context?.contentResolver
        val rs = resolver?.query(CallLog.Calls.CONTENT_URI, arrayOf(CallLog.Calls.NUMBER),
            null,null,"${CallLog.Calls.DATE} DESC")
        rs?.moveToFirst()
        val number = rs?.getString(rs.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
        rs?.close()
        return number
    }
}