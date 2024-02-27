package com.example.marketapp.data.mappers

import com.example.marketapp.data.network.models.RetrieveProductDto
import com.example.marketapp.domain.entities.ListProduct
import javax.inject.Inject

class RetrieveProductMapper @Inject constructor(
    private val categoryMapper: CategoryMapper
) : DomainMapper<RetrieveProductDto, ListProduct> {

    override fun mapToDomain(input: RetrieveProductDto): ListProduct {
        return ListProduct(
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