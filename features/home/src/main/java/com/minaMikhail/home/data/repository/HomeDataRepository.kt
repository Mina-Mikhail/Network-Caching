package com.minaMikhail.home.data.repository

import com.minaMikhail.base.network.Resource
import com.minaMikhail.home.data.dataSource.local.HomeLocalDataSource
import com.minaMikhail.home.data.dataSource.remote.HomeRemoteDataSource
import com.minaMikhail.home.data.mapper.NewsMapper
import com.minaMikhail.home.domain.model.HomeArticle
import com.minaMikhail.home.domain.repository.HomeRepository
import com.minaMikhail.network.annotations.SupportCache
import com.minaMikhail.preferences.appPreferences.AppPreferences
import com.minaMikhail.preferences.rateLimiterPreferences.RateLimiterPreferences
import com.minaMikhail.preferences.rateLimiterPreferences.enums.RateLimiterPreferencesKey
import com.minaMikhail.utils.coroutineDispatchers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeDataRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val rateLimiterPreferences: RateLimiterPreferences,
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeLocalDataSource: HomeLocalDataSource,
    private val appPreferences: AppPreferences,
    private val newsMapper: NewsMapper
) : HomeRepository {

    override suspend fun getNews(isForceRefresh: Boolean): Flow<Resource<List<HomeArticle>?>> {
        return execute(
            dispatcher = ioDispatcher,
            isCacheSupported = appPreferences.isCachingEnabled() && isNewsApiSupportCache(),
            shouldFetchFromRemote = isForceRefresh || rateLimiterPreferences.shouldFetch(
                RateLimiterPreferencesKey.NEWS_FEED_API,
                getNewsApiCacheDuration()
            ),
            fetchFromRemote = {
                homeRemoteDataSource.getNews(
                    "Tesla",
                    "2024-05-30",
                    "5c203f74fdcc4265bca981fd059fee2c"
                )
            },
            mapRemoteToDomain = {
                newsMapper.map(it)
            },
            saveRemoteData = {

                homeLocalDataSource.saveNews(it)
                rateLimiterPreferences.fetched(RateLimiterPreferencesKey.NEWS_FEED_API)
            },
            fetchFromLocal = {
                homeLocalDataSource.getNews()
            }
        )
    }

    private fun isNewsApiSupportCache(): Boolean {
        HomeRemoteDataSource::getNews.annotations.find {
            it.annotationClass.java == SupportCache::class.java
        }?.let {
            return true
        } ?: run {
            return false
        }
    }

    private fun getNewsApiCacheDuration(): Long {
        HomeRemoteDataSource::getNews.annotations.find {
            it.annotationClass.java == SupportCache::class.java
        }?.let {
            return (it as SupportCache).cacheDurationSeconds
        } ?: run {
            return 0L
        }
    }
}