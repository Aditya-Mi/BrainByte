package com.example.brainbyte

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UrlApi {
    @GET("?")
    fun getData(
        @Query("key") key: String,
        @Query("url") url: String
    ): Call<UrlData>
}