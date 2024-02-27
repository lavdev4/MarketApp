package com.example.marketapp.domain.entities

data class ListProduct(
    val category: Category,
    val name: String,
    val details: String,
    val size: String,
    val colour: String,
    val price: Int,
    val mainImage: String,
    val id: Int
)
