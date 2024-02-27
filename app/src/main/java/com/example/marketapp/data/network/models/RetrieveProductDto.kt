package com.example.marketapp.data.network.models

import com.google.gson.annotations.SerializedName

data class RetrieveProductDto(
    @SerializedName("category") val category: ReturnCategoryDto,
    @SerializedName("name") val name: String,
    @SerializedName("details") val details: String,
    @SerializedName("size") val size: String,
    @SerializedName("colour") val colour: String,
    @SerializedName("price") val price: Int,
    @SerializedName("main_image") val mainImage: String,
    @SerializedName("id") val id: Int
)