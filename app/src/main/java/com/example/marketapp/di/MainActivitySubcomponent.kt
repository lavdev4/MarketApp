package com.example.marketapp.di

import com.example.marketapp.di.annotations.MainActivityScope
import com.example.marketapp.presentation.MainActivity
import com.example.marketapp.presentation.screens.ProductsDetailFragment
import com.example.marketapp.presentation.screens.ProductsListFragment
import dagger.Subcomponent

@MainActivityScope
@Subcomponent
interface MainActivitySubcomponent {

    fun inject(impl: MainActivity)

    fun inject(impl: ProductsListFragment)

    fun inject(impl: ProductsDetailFragment)

    @Subcomponent.Builder
    interface MainActivitySubcomponentBuilder {
        fun build(): MainActivitySubcomponent
    }
}