package com.example.marketapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.domain.entities.ProductDetail
import com.example.marketapp.domain.usecases.GetProductsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailScreenVM @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    private val _productData = MutableLiveData<Result<ProductDetail>?>(null)
    val productData: LiveData<Result<ProductDetail>?>
        get() = _productData

    fun initialize(productId: Int){
        viewModelScope.launch {
            _productData.value = getProductsUseCase.getProductDetails(productId)
        }
    }
}