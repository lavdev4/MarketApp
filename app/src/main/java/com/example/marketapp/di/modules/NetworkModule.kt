package com.example.marketapp.di.modules

import com.example.marketapp.data.network.apiservice.MainApiService
import com.example.marketapp.di.annotations.ApiBaseUrl
import com.example.marketapp.di.annotations.ApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
interface NetworkModule {

    companion object {
        @ApplicationScope
        @Provides
        fun provideGson(): Gson {
            return GsonBuilder()
                .setPrettyPrinting()
                .create()
        }

        @ApplicationScope
        @Provides
        fun provideConverterFactory(gson: Gson): Converter.Factory {
            return GsonConverterFactory.create(gson)
        }

        @ApplicationScope
        @Provides
        fun provideRetrofit(
            @ApiBaseUrl apiBaseUrl: String,
            converterFactory: Converter.Factory
        ): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .baseUrl(apiBaseUrl)
                .build()
        }

        @ApplicationScope
        @Provides
        fun provideMainApiService(retrofit: Retrofit): MainApiService {
            return retrofit.create(MainApiService::class.java)
        }
    }
}