package com.demo.medius.data.model

import com.google.gson.annotations.SerializedName

data class OTPInput(
    @SerializedName("otp") var empId: String? = null,
)
