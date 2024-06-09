package com.minaMikhail.base.repository

import com.minaMikhail.base.network.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface BaseRepository {
    suspend fun <DOMAIN, REMOTE> execute(
        dispatcher: CoroutineDispatcher,
        isCacheSupported: Boolean? = null,
        shouldFetchFromRemote: Boolean,
        fetchFromRemote: suspend () -> REMOTE,
        mapRemoteToDomain: (REMOTE) -> DOMAIN,
        saveRemoteData: (suspend (DOMAIN) -> Unit)? = null,
        fetchFromLocal: (suspend () -> DOMAIN?)? = null
    ): Flow<Resource<DOMAIN?>> = flow {
        emit(Resource.Loading)

        if (shouldFetchFromRemote) {
            val remoteModel = fetchFromRemote()
            val domainModel = mapRemoteToDomain(remoteModel)

            if (isCacheSupported == true) {
                saveRemoteData?.invoke(domainModel)
            }

            emit(Resource.Success(domainModel))
        } else {
            emit(Resource.Success(fetchFromLocal?.invoke()))
        }
    }
        .flowOn(dispatcher)
}