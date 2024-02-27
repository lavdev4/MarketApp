package com.example.marketapp.data.mappers

import com.example.marketapp.data.network.models.ImageDto
import com.example.marketapp.domain.entities.Image
import javax.inject.Inject

class ImageMapper @Inject constructor() : DomainMapper<ImageDto, Image> {

    override fun mapToDomain(input: ImageDto): Image {
        return Image(image = input.image)
    }
}