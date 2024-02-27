package com.example.marketapp.di.modules

import com.example.marketapp.data.repositories.ProductsRepositoryImpl
import com.example.marketapp.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindProductsRepository(repository: ProductsRepositoryImpl): ProductsRepository
}