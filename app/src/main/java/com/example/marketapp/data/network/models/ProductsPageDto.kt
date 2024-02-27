package com.example.marketapp.data.network.models

import com.google.gson.annotations.SerializedName

data class ProductsPageDto(
    @SerializedName("count") val count: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("results") val results: List<RetrieveProductDto>
)