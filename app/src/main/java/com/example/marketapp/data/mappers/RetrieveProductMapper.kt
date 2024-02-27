package com.example.marketapp.data.mappers

import com.example.marketapp.data.network.models.RetrieveProductDto
import com.example.marketapp.domain.entities.Product
import javax.inject.Inject

class RetrieveProductMapper @Inject constructor(
    private val categoryMapper: CategoryMapper
) : DomainMapper<RetrieveProductDto, Product> {

    override fun mapToDomain(input: RetrieveProductDto): Product {
        return Product(
            category = categoryMapper.mapToDomain(input.category),
            name = input.name,
            details = input.details,
            size = input.size,
            colour = input.colour,
            price = input.price,
            mainImage = input.mainImage,
            id = input.id
        )
    }
}