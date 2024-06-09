package com.minaMikhail.base.useCase

import com.minaMikhail.base.extensions.obtainOutcome
import com.minaMikhail.base.network.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, Result>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    operator fun invoke(parameters: Params): Flow<Resource<Result>> =
        execute(parameters)
            .obtainOutcome()
            .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: Params): Flow<Result>
}