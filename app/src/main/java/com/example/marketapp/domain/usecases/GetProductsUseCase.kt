package com.example.marketapp.domain.usecases

import androidx.paging.PagingData
import com.example.marketapp.domain.entities.ProductDetail
import com.example.marketapp.domain.entities.Product
import com.example.marketapp.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    operator fun invoke(): Flow<PagingData<Product>> = productsRepository.getProductsList()

    suspend fun getProductDetails(id: Int): Result<ProductDetail> {
        return productsRepository.getProductInfo(id)
    }
}