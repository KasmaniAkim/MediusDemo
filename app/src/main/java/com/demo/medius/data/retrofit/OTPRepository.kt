package com.demo.medius.data.retrofit

import com.demo.medius.data.model.OTPInput
import retrofit2.Retrofit

class OTPRepository(retrofit: Retrofit) {
    private val services = retrofit.create(CustomApi::class.java)
    suspend fun checkOTP(login: OTPInput) = services.verifyOTP(login)
}