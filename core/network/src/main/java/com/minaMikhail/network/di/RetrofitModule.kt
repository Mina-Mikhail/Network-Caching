package com.minaMikhail.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.minaMikhail.network.utils.applyCommonConfiguration
import com.minaMikhail.network.utils.Headers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    
    @Provides
    @Singleton
    fun provideHeadersInterceptor(): Interceptor =
        Interceptor { chain ->
            val newRequest = chain
                .request()
                .newBuilder()
                .addHeader(Headers.Keys.ACCEPT, Headers.Values.ACCEPT_VALUE)
                .addHeader(Headers.Keys.CONTENT_TYPE, Headers.Values.ACCEPT_VALUE)
                .build()
            chain.proceed(newRequest)
        }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headersInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient
        .Builder()
        .applyCommonConfiguration(
            headersInterceptor = headersInterceptor,
            loggingInterceptor = loggingInterceptor
        )
        .retryOnConnectionFailure(false)
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .baseUrl("https://newsapi.org/v2/")
        .build()
}