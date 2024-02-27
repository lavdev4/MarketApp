package com.example.marketapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.marketapp.data.mappers.RetrieveProductMapper
import com.example.marketapp.data.mappers.ReturnProductMapper
import com.example.marketapp.data.network.apiservice.MainApiService
import com.example.marketapp.data.network.pagings.ProductsPagingSource
import com.example.marketapp.domain.entities.Product
import com.example.marketapp.domain.entities.ProductDetail
import com.example.marketapp.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val mainApiService: MainApiService,
    private val productsPagingSource: ProductsPagingSource,
    private val retrieveProductsMapper: RetrieveProductMapper,
    private val returnProductMapper: ReturnProductMapper
): ProductsRepository {

    override fun getProductsList(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { productsPagingSource }
        ).flow.map { pagingData ->
            pagingData.map { retrieveProductsMapper.mapToDomain(it) }
        }
    }

    override suspend fun getProductInfo(id: Int): Result<ProductDetail> {
        return try {
            val response = mainApiService.getProductInfo(id).body()
            response?.let {
                Result.success(returnProductMapper.mapToDomain(response))
            } ?: run {
                Result.failure(Exception("Response is empty."))
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}