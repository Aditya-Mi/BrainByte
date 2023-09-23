package com.example.brainbyte.fragment

import android.nfc.Tag
import android.os.Bundle
import android.provider.CallLog
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brainbyte.*
import com.example.brainbyte.database.SpamNumber
import com.example.brainbyte.database.SpamViewModel
import com.example.brainbyte.databinding.FragmentRecentBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


const val TAG:String = "RecentFragment"
class RecentFragment : Fragment() {
    private val mViewModel: SpamViewModel by viewModels()
    private var _binding: FragmentRecentBinding? = null
    private val binding get() = _binding!!
    private var callLogList = kotlin.collections.ArrayList<CallLogModel>()
    private val adapter : CallLogAdapter by lazy { CallLogAdapter(callLogList) }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentRecentBinding.inflate(inflater,container,false)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mViewModel.insertNumber(SpamNumber("+916202592138",3))
        readLog()

        return binding.root
    }
    private fun readLog(){
        val cols = arrayOf(CallLog.Calls._ID,CallLog.Calls.CACHED_NAME,CallLog.Calls.NUMBER,CallLog.Calls.TYPE,CallLog.Calls.DATE)
        val resolver = requireActivity().contentResolver
        val rs = resolver.query(CallLog.Calls.CONTENT_URI,cols,null,null,
            "${CallLog.Calls.DATE} DESC")
        callLogList.clear()
        if (rs != null) {
            while (rs.moveToNext()){
                val number = rs.getString(rs.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
                var callType = rs.getString(rs.getColumnIndexOrThrow(CallLog.Calls.TYPE))
                val fullDate = rs.getLong(rs.getColumnIndexOrThrow(CallLog.Calls.DATE))
                var contactName = rs.getString(rs.getColumnIndexOrThrow(CallLog.Calls.CACHED_NAME))
                contactName = if(contactName == null || contactName.equals("")) {
                    "Unknown"
                } else {
                    contactName
                }

                val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                val date = Date(fullDate)
                val time = sdf.format(date)
                //val formattedTime = getFormattedDateTime(time,"HH:mm:ss","hh:mm a")
                callType = when(Integer.parseInt(callType)){
                    CallLog.Calls.INCOMING_TYPE-> "Incoming"
                    CallLog.Calls.OUTGOING_TYPE-> "Outgoing"
                    CallLog.Calls.MISSED_TYPE-> "Incoming"
                    CallLog.Calls.REJECTED_TYPE-> "Incoming"
                    else-> "NA"
                }
//                var reports: Int = 0
//                val mNumber = mViewModel.searchDatabase(number)
//                Log.i(TAG,"mNumber = ${mNumber.value?.reports} + ${mNumber.value?.number}")
//                if(mNumber.value?.number.equals(null)){
//                    reports=0
//                }else{
//                    reports= mNumber.value?.reports ?: 0
//                }

                val callLogItem = CallLogModel(number,contactName,callType,time)
                callLogList.add(callLogItem)
            }
            adapter.notifyDataSetChanged()
        }
        rs?.close()
    }
    private fun getFormattedDateTime(dateStr: String, strInputFormat:String,
                                     strOutputFormat:String):String{
        var formattedDate = dateStr
        val inputFormat = SimpleDateFormat(strInputFormat, Locale.getDefault())
        val outputFormat = SimpleDateFormat(strOutputFormat, Locale.getDefault())
        var date:Date? = null
        try {
            date=inputFormat.parse(strInputFormat)
        } catch (_:ParseException){
        }
        if(date!= null){
            formattedDate = outputFormat.format(date)
        }
        return formattedDate

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}


