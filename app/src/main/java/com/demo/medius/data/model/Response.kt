package com.demo.medius.data.model

import com.google.gson.annotations.SerializedName

data class Response<out T>(
    @SerializedName("status")
    var status: String,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("data")
    val `data`: T?,
)