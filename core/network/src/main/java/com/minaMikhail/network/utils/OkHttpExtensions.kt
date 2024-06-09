package com.minaMikhail.network.utils

import com.minaMikhail.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val REQUEST_TIME_OUT: Long = 60

fun OkHttpClient.Builder.applyCommonConfiguration(
    headersInterceptor: Interceptor,
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient.Builder {
    this
        .readTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor)
    if (BuildConfig.DEBUG) {
        this
            .addNetworkInterceptor(loggingInterceptor)
    }
    return this
}