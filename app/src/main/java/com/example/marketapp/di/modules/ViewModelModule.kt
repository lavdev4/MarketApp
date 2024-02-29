package com.example.marketapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.marketapp.di.annotations.ViewModelKey
import com.example.marketapp.presentation.viewmodels.ProductsVM
import com.example.marketapp.presentation.viewmodels.ScreenStateVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @IntoMap
    @ViewModelKey(ScreenStateVM::class)
    @Binds
    abstract fun bindMainActivitySharedVM(impl: ScreenStateVM): ViewModel

    @IntoMap
    @ViewModelKey(ProductsVM::class)
    @Binds
    abstract fun bindProductsVM(impl: ProductsVM): ViewModel
}