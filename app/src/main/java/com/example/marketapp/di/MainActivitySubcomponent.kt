package com.example.marketapp.di

import com.example.marketapp.di.annotations.MainActivityScope
import com.example.marketapp.presentation.MainActivity
import dagger.Subcomponent

@MainActivityScope
@Subcomponent
interface MainActivitySubcomponent {

    fun inject(impl: MainActivity)

    @Subcomponent.Builder
    interface MainActivitySubcomponentBuilder {
        fun build(): MainActivitySubcomponent
    }
}