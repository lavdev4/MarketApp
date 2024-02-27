package com.example.marketapp.data.network.models

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("id") val id: Int,
    @SerializedName("modified_at") val modifiedAt: String,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("image") val image: String? = null,
    @SerializedName("rating") val rating: Int,
    @SerializedName("message") val message: String
)