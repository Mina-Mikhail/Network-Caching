package com.minaMikhail.home.domain.useCase

import com.minaMikhail.base.network.Resource
import com.minaMikhail.home.domain.model.HomeArticle
import com.minaMikhail.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: HomeRepository
) {

    suspend operator fun invoke(isForceRefresh: Boolean = false): Flow<Resource<List<HomeArticle>?>> {
        return repository.getNews(isForceRefresh)
    }
}