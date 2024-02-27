package com.example.marketapp.data.mappers

import com.example.marketapp.data.network.models.ReviewDto
import com.example.marketapp.domain.entities.Review
import javax.inject.Inject

class ReviewMapper @Inject constructor() : DomainMapper<ReviewDto, Review> {

    override fun mapToDomain(input: ReviewDto): Review {
        return Review(
            id = input.id,
            modifiedAt = input.modifiedAt,
            createdAt = input.createdAt,
            firstName = input.firstName,
            lastName = input.lastName,
            image = input.image,
            rating = input.rating,
            message = input.message
        )
    }
}