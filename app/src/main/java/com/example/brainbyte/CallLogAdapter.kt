package com.example.brainbyte

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CallLogAdapter( private val callLogList : ArrayList<CallLogModel>): RecyclerView.Adapter<CallLogAdapter.CallLogViewHolder>() {
    private lateinit var context: Context

    class CallLogViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var tvRecentContactName:TextView = itemView.findViewById(R.id.tvRecentContactName)
        var tvRecentContactNumber:TextView = itemView.findViewById(R.id.recentContactNumber)
        var tvRecentContactTime:TextView = itemView.findViewById(R.id.tvRecentContactTime)
        var ivRecentCallType: ImageView = itemView.findViewById(R.id.ivRecentCallType)
        var callLogBackground:ConstraintLayout = itemView.findViewById(R.id.callLogBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallLogViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recent_call_layout,parent,false)
        context= parent.context
        return CallLogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return callLogList.size
    }

    override fun onBindViewHolder(holder: CallLogViewHolder, position: Int) {
        holder.tvRecentContactName.text = callLogList[position].contactName
        holder.tvRecentContactNumber.text = callLogList[position].contactNumber
        holder.tvRecentContactTime.text = callLogList[position].callTime
        if(callLogList[position].callType=="Outgoing"){
            holder.ivRecentCallType.setImageResource(R.drawable.baseline_arrow_outward_24)
        }else{
            holder.ivRecentCallType.setImageResource(R.drawable.diagonal_arrow_left_down_svgrepo_com)
        }
        holder.callLogBackground.setOnClickListener {
            addNumber(callLogList[position].contactNumber)
        }
        if(callLogList[position].contactName=="Sachita" || callLogList[position].contactNumber=="+918295269005"){
            holder.callLogBackground.setBackgroundColor(Color.parseColor("#853535"))
        }else{
            holder.callLogBackground.setBackgroundColor(Color.parseColor("#010101"))
        }

    }
    private fun addNumber(number:String){
        val retrofit = RetrofitClient.buildService(NumberApi::class.java)
        val postRequestBody = PostRequestBody(number)
        val result = retrofit.postNumber(postRequestBody)
        result.enqueue(object : retrofit2.Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    if(responseBody.status == "Success"){
                        Toast.makeText(context,"Number successfully added as Spam",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Api call failed",Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(context,"Api call failed",Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Toast.makeText(context,"Api call failed",Toast.LENGTH_LONG).show()
            }
        })
    }

}


