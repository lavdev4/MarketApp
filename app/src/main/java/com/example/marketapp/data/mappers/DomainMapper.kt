package com.example.marketapp.data.mappers

interface DomainMapper<FROM, TO> {
    fun mapToDomain(input: FROM): TO
}