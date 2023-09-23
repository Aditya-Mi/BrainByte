package com.example.brainbyte

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import retrofit2.Call
import retrofit2.http.*

interface NumberApi {
    @GET("/spam/check")
    fun getNumber(
        @Path("number") number:String
    ): Call<SpamNumberResponse>

    @POST("report")
    fun postNumber(@Body postRequestBody: PostRequestBody):Call<PostResponse>
}