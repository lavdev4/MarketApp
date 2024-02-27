package com.example.marketapp.data.network.models

import com.google.gson.annotations.SerializedName

data class ReturnProductDto(
    @SerializedName("category") val category: ReturnCategoryDto,
    @SerializedName("name") val name: String,
    @SerializedName("details") val details: String,
    @SerializedName("size") val size: String,
    @SerializedName("colour") val colour: String,
    @SerializedName("price") val price: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("main_image") val mainImage: String,
    @SerializedName("images") val images: List<ImageDto>,
    @SerializedName("reviews") val reviews: List<ReviewDto>
)