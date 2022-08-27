package com.cinema.emovie.data.di

import com.cinema.emovie.BuildConfig
import com.cinema.emovie.common.API_URL_BASE
import com.cinema.emovie.data.model.ApiKeyModel
import com.cinema.emovie.data.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun okHttpClientProvider() = OkHttpClient.Builder().apply {
        readTimeout(30L, TimeUnit.SECONDS)
        connectTimeout(30L, TimeUnit.SECONDS)
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(this)
        }
    }.build()

    @Singleton
    @Provides
    fun retrofitProvider(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(API_URL_BASE)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun apiServiceProvider(retrofit: Retrofit): ApiServices = retrofit
        .create(ApiServices::class.java)

    @Singleton
    @Provides
    fun apiKeyProvider() = ApiKeyModel(BuildConfig.API_KEY)
}