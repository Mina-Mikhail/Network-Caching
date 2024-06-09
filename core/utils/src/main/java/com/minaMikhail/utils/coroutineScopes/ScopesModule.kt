package com.minaMikhail.utils.coroutineScopes

import com.minaMikhail.utils.coroutineDispatchers.DefaultDispatcher
import com.minaMikhail.utils.coroutineDispatchers.IoDispatcher
import com.minaMikhail.utils.coroutineDispatchers.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScopesModule {

    @Provides
    @Singleton
    @DefaultScope
    fun providesDefaultCoroutineScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @Provides
    @Singleton
    @IoScope
    fun providesIoCoroutineScope(
        @IoDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @Provides
    @Singleton
    @MainScope
    fun providesMainCoroutineScope(
        @MainDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)
}