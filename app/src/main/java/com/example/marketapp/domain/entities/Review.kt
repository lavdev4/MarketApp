package com.example.marketapp.domain.entities

data class Review(
    val id: Int,
    val modifiedAt: String,
    val createdAt: String? = null,
    val firstName: String,
    val lastName: String,
    val image: String? = null,
    val rating: Int,
    val message: String
)