package com.example.marketapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.marketapp.di.annotations.ViewModelKey
import com.example.marketapp.presentation.viewmodels.DetailScreenVM
import com.example.marketapp.presentation.viewmodels.ProductsScreenVM
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
    @ViewModelKey(ProductsScreenVM::class)
    @Binds
    abstract fun bindProductsScreenVM(impl: ProductsScreenVM): ViewModel

    @IntoMap
    @ViewModelKey(DetailScreenVM::class)
    @Binds
    abstract fun bindDetailScreenVM(impl: DetailScreenVM): ViewModel
}