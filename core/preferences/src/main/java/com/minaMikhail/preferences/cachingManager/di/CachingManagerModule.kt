package com.minaMikhail.preferences.cachingManager.di

import com.minaMikhail.preferences.cachingManager.CachingManager
import com.minaMikhail.preferences.cachingManager.CachingManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
fun interface CachingManagerModule {

    @Binds
    @Singleton
    fun bindCachingManager(
        cachingManagerImpl: CachingManagerImpl
    ): CachingManager
}