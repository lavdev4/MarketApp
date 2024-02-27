package com.example.marketapp.domain.entities

import com.example.marketapp.data.network.models.ImageDto
import com.example.marketapp.data.network.models.ReturnCategoryDto
import com.example.marketapp.data.network.models.ReviewDto

data class DetailProduct(
    val category: Category,
    val name: String,
    val details: String,
    val size: String,
    val colour: String,
    val price: Int,
    val id: Int,
    val mainImage: String,
    val images: List<Image>,
    val reviews: List<Review>
)
