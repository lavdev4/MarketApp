package com.example.marketapp.data.mappers

import com.example.marketapp.data.network.models.ReturnProductDto
import com.example.marketapp.domain.entities.DetailProduct
import com.example.marketapp.domain.entities.Image
import javax.inject.Inject

class ReturnProductMapper @Inject constructor(
    private val categoryMapper: CategoryMapper,
    private val reviewMapper: ReviewMapper,
    private val imageMapper: ImageMapper
) : DomainMapper<ReturnProductDto, DetailProduct> {

    override fun mapToDomain(input: ReturnProductDto): DetailProduct {
        return DetailProduct(
            category = categoryMapper.mapToDomain(input.category),
            name = input.name,
            details = input.details,
            size = input.size,
            colour = input.colour,
            price = input.price,
            id = input.id,
            mainImage = input.mainImage,
            images = input.images.map { imageMapper.mapToDomain(it) },
            reviews = input.reviews.map { reviewMapper.mapToDomain(it) }
        )
    }
}