package com.mrenann.globoplay.core.di

import com.mrenann.globoplay.BuildConfig
import com.mrenann.globoplay.core.data.remote.ParamsInterceptor
import com.mrenann.globoplay.core.data.remote.TVService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT_SECONDS = 15L

val networkModule = module {

    // Provide ParamsInterceptor
    single { ParamsInterceptor() }


    single {
        OkHttpClient.Builder()
            .addInterceptor(get<ParamsInterceptor>()) // Inject ParamsInterceptor
            .addInterceptor(get<HttpLoggingInterceptor>()) // Inject LoggingInterceptor
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    single { GsonConverterFactory.create() }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL) // Use your BASE_URL from BuildConfig
            .client(get<OkHttpClient>()) // Inject OkHttpClient
            .addConverterFactory(get<GsonConverterFactory>()) // Inject GsonConverterFactory
            .build()
            .create(TVService::class.java)
    }
}