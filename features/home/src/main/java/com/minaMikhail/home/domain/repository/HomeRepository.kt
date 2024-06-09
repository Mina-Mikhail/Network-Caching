package com.minaMikhail.home.domain.repository

import com.minaMikhail.base.network.Resource
import com.minaMikhail.base.repository.BaseRepository
import com.minaMikhail.home.domain.model.HomeArticle
import kotlinx.coroutines.flow.Flow

interface HomeRepository : BaseRepository {

    suspend fun getNews(isForceRefresh: Boolean): Flow<Resource<List<HomeArticle>?>>
}