package com.example.marketapp.domain.entities

data class ProductDetail(
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
