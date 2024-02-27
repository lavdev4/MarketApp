package com.example.marketapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.marketapp.di.annotations.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

//    @IntoMap
//    @ViewModelKey(RegisterViewModel::class)
//    @Binds
//    abstract fun bindRegisterViewModel(impl: RegisterViewModel): LoginGraphViewModel
}