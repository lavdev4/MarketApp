package com.example.marketapp.data.mappers

import com.example.marketapp.data.network.models.ReturnCategoryDto
import com.example.marketapp.domain.entities.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() : DomainMapper<ReturnCategoryDto, Category> {

    override fun mapToDomain(input: ReturnCategoryDto): Category {
        return Category(
            name = input.name,
            icon = input.icon,
            id = input.id
        )
    }
}