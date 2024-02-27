package com.example.marketapp.di

import android.app.Application
import android.content.Context
import com.example.marketapp.di.modules.NetworkModule
import com.example.marketapp.di.modules.ViewModelModule
import com.example.marketapp.di.annotations.ApiBaseUrl
import com.example.marketapp.di.annotations.ApplicationContext
import com.example.marketapp.di.annotations.ApplicationScope
import com.example.marketapp.di.modules.DataModule
import com.example.marketapp.presentation.MarketApplication
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [
    ViewModelModule::class,
    NetworkModule::class,
    DataModule::class
])
interface ApplicationComponent {

    fun inject(app: MarketApplication)

    fun activitySubcomponent(): MainActivitySubcomponent.MainActivitySubcomponentBuilder

    @Component.Builder
    interface ApplicationComponentBuilder {

        @BindsInstance
        fun appContext(@ApplicationContext context: Context) : ApplicationComponentBuilder

        @BindsInstance
        fun apiBaseUrl(@ApiBaseUrl url: String): ApplicationComponentBuilder

        fun build(): ApplicationComponent
    }
}