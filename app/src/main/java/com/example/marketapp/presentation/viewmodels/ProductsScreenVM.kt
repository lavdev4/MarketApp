package com.example.marketapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marketapp.domain.usecases.GetProductsUseCase
import javax.inject.Inject

class ProductsScreenVM @Inject constructor(
    getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    val productsFlow = getProductsUseCase().cachedIn(viewModelScope)
}