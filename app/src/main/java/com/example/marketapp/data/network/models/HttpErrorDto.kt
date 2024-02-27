package com.example.marketapp.data.network.models

import com.google.gson.annotations.SerializedName

data class HttpErrorDto(
    val message: String,
    @SerializedName("error")
    val code: Int
)