package com.minaMikhail.home.data.dataSource.remote

import com.minaMikhail.home.data.response.NewsResponse
import com.minaMikhail.network.annotations.SupportCache
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeRemoteDataSource {

    @GET("everything")
    @SupportCache(cacheDurationSeconds = 3600)
    suspend fun getNews(
        @Query("q") query: String?,
        @Query("from") from: String?,
        @Query("apiKey") apiKey: String?
    ): NewsResponse
}