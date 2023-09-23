package com.example.brainbyte.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.brainbyte.UrlApi
import com.example.brainbyte.UrlData
import com.example.brainbyte.databinding.FragmentUrlBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder

const val BASE_URL = "https://ipqualityscore.com/api/json/url/"
const val key = "bSwU0w3hlRlCcYOAfXflMAY44azVQp0a"
class UrlFragment : Fragment() {
    private var _binding: FragmentUrlBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUrlBinding.inflate(inflater,container,false)
        if(isAdded && arguments != null && requireArguments().containsKey("Url")){
            val url = requireArguments().getString("Url").toString()
            binding.etUrl.setText(url)
            val finalUrl = URLEncoder.encode(url, "UTF-8")
            checkMaliciousUrl(finalUrl)
        }
        binding.btCheck.setOnClickListener {
            if(binding.etUrl.text.isEmpty()){
                Toast.makeText(requireContext(),"Url field cannot be empty",Toast.LENGTH_SHORT).show()
            }else {
                val url = binding.etUrl.text.toString()
                val finalUrl = URLEncoder.encode(url, "UTF-8")
                checkMaliciousUrl(finalUrl)
            }
        }

//        binding.browser.setOnClickListener {
//            if(binding.etUrl.text.isEmpty()){
//                Toast.makeText(requireContext(),"Url field cannot be empty",Toast.LENGTH_SHORT).show()
//            }else {
//                val url = binding.etUrl.text.toString()
//                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                startActivity(browserIntent)
//            }
//        }
        return binding.root
    }

    private fun checkMaliciousUrl(finalUrl: String) {
        val httpClient = OkHttpClient.Builder()
        val client = httpClient.build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(UrlApi::class.java)
        val retrofitData = retrofit.getData(key,finalUrl)

        retrofitData.enqueue(object : Callback<UrlData?> {
            override fun onResponse(call: Call<UrlData?>, response: Response<UrlData?>) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.success){
                    val score = responseBody.risk_score.toString()
//                    binding.score.text=score
//                    if(responseBody.risk_score==0){
//                        val description = "the safe meter has a rating of $score for this app. this site might have no privacy and security issues. You have no risk"
//                        binding.tvUrlDescription.text=description
//                        binding.tvUrlDescription.visibility = View.VISIBLE
//                        binding.progressBar.setProgress(0,true)
//                    }else if(responseBody.risk_score<=25){
//                        val description = "the safe meter has a rating of $score for this app. this app might have least privacy and security issues. You may have least risk"
//                        binding.tvUrlDescription.text=description
//                        binding.tvUrlDescription.visibility = View.VISIBLE
//                        binding.progressBar.setProgress(responseBody.risk_score,true)
//                    }else if(responseBody.risk_score<=50){
//                        val description = "the safe meter has a rating of $score for this app. this app might have privacy and security issues. You may have lower risk"
//                        binding.tvUrlDescription.text=description
//                        binding.tvUrlDescription.visibility = View.VISIBLE
//                        binding.progressBar.setProgress(responseBody.risk_score,true)
//                    }else if(responseBody.risk_score<=75){
//                        val description = "the safe meter has a rating of $score for this app. this app might have privacy and security issues. You may have higher risk"
//                        binding.tvUrlDescription.text=description
//                        binding.tvUrlDescription.visibility = View.VISIBLE
//                        binding.progressBar.setProgress(responseBody.risk_score,true)
//                    }else{
//                        val description = "the safe meter has a rating of $score for this app. this app might have privacy and security issues. You may have highest risk"
//                        binding.tvUrlDescription.text=description
//                        binding.tvUrlDescription.visibility = View.VISIBLE
//                        binding.progressBar.setProgress(responseBody.risk_score,true)
//                    }
                }else{
                    Toast.makeText(requireContext(),"Api called failed",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UrlData?>, t: Throwable) {
                Toast.makeText(requireContext(),"Api call failed",Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}