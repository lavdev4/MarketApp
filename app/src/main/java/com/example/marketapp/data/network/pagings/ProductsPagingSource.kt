package com.example.marketapp.data.network.pagings

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marketapp.data.network.apiservice.MainApiService
import com.example.marketapp.data.network.models.RetrieveProductDto
import javax.inject.Inject

class ProductsPagingSource @Inject constructor(
    private val mainApiService: MainApiService
) : PagingSource<Int, RetrieveProductDto>() {

    override fun getRefreshKey(state: PagingState<Int, RetrieveProductDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RetrieveProductDto> {
        val loadPageNumber = params.key ?: 1
        return try {
            val response = mainApiService.getProductsPage(loadPageNumber, params.loadSize)
            response.body()?.let { page ->
                val nextPage =
                    if (page.currentPage < page.totalPages) page.currentPage + 1 else null
                LoadResult.Page(
                    data = page.results,
                    prevKey = null,
                    nextKey = nextPage
                )
            } ?: run { LoadResult.Error(Exception("Response is empty.")) }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}