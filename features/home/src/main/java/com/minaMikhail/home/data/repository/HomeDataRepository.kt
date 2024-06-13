package com.minaMikhail.home.data.repository

import com.minaMikhail.base.network.Resource
import com.minaMikhail.home.data.dataSource.remote.HomeRemoteDataSource
import com.minaMikhail.home.data.mapper.NewsMapper
import com.minaMikhail.home.domain.model.HomeArticle
import com.minaMikhail.home.domain.repository.HomeRepository
import com.minaMikhail.network.utils.getCacheDuration
import com.minaMikhail.network.utils.isSupportCache
import com.minaMikhail.preferences.cachingManager.CachingManager
import com.minaMikhail.preferences.cachingManager.utils.CachingPreferenceKey.NEWS_KEY
import com.minaMikhail.preferences.cachingManager.utils.CachingPreferenceKey.REMOTE_CONFIG_CACHING_KEY
import com.minaMikhail.preferences.rateLimiter.RateLimiter
import com.minaMikhail.preferences.rateLimiter.utils.RateLimiterPreferenceKey
import com.minaMikhail.utils.coroutineDispatchers.IoDispatcher
import com.minaMikhail.utils.extensions.toJsonList
import com.minaMikhail.utils.extensions.toJsonString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeDataRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val rateLimiter: RateLimiter,
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val cachingManager: CachingManager,
    private val newsMapper: NewsMapper
) : HomeRepository {

    override suspend fun getNews(isForceRefresh: Boolean): Flow<Resource<List<HomeArticle>?>> {
        return execute(
            dispatcher = ioDispatcher,
            isCacheSupported = cachingManager.getFirst(REMOTE_CONFIG_CACHING_KEY, false)
                    && HomeRemoteDataSource::getNews.isSupportCache(),
            shouldFetchFromRemote = isForceRefresh || rateLimiter.shouldFetch(
                RateLimiterPreferenceKey.NEWS_FEED_API,
                HomeRemoteDataSource::getNews.getCacheDuration()
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
                rateLimiter.fetched(RateLimiterPreferenceKey.NEWS_FEED_API)

                cachingManager.set(NEWS_KEY, it.take(4).toJsonString())
            },
            fetchFromLocal = {
                cachingManager.getFirst(NEWS_KEY, "").toJsonList<List<HomeArticle>>()
            }
        )
    }
}