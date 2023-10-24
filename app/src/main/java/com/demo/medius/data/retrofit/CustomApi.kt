package com.demo.medius.data.retrofit

import com.demo.medius.data.model.OTPData
import com.demo.medius.data.model.OTPInput
import com.demo.medius.data.model.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomApi {

    @POST("verifyOTP")
    suspend fun verifyOTP(
        @Body body: OTPInput
    ): Response<OTPData>

}