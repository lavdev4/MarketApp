package com.example.marketapp.data.network.apiservice

import androidx.paging.PagingData
import com.example.marketapp.data.network.models.ProductsPageDto
import com.example.marketapp.data.network.models.ReturnProductDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApiService {

    @GET("/products")
    suspend fun getProductsPage(
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_PAGE_SIZE) pageSize: Int
    ): Response<ProductsPageDto>

    @GET("/products/{id}")
    suspend fun getProductInfo(
        @Path("id") productId: Int
    ): Response<ReturnProductDto>

    companion object {
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_PAGE_SIZE = "page_size"
    }
}