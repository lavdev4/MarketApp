package com.example.marketapp.data.network.models

import com.google.gson.annotations.SerializedName

data class ReturnCategoryDto(
    @SerializedName("name") val name: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int
)