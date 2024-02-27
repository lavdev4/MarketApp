package com.example.marketapp.domain.repositories

import androidx.paging.PagingData
import com.example.marketapp.domain.entities.ProductDetail
import com.example.marketapp.domain.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getProductsList(): Flow<PagingData<Product>>

    suspend fun getProductInfo(id: Int): Result<ProductDetail>
}