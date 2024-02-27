package com.example.marketapp.presentation

import android.app.Application
import com.example.marketapp.BuildConfig
import com.example.marketapp.di.ApplicationComponent
import com.example.marketapp.di.DaggerApplicationComponent

class MarketApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        applicationComponent = DaggerApplicationComponent.builder()
            .appContext(applicationContext)
            .apiBaseUrl(API_BASE_URL)
            .build()
        super.onCreate()
    }

    companion object {
        private const val API_BASE_URL = BuildConfig.API_BASE_URL
    }
}