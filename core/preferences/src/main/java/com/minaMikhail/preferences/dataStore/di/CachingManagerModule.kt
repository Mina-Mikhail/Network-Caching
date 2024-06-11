package com.minaMikhail.preferences.dataStore.di

import com.minaMikhail.preferences.dataStore.CachingManager
import com.minaMikhail.preferences.dataStore.CachingManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CachingManagerModule {

    @Binds
    @Singleton
    fun bindCachingManager(
        cachingManagerImpl: CachingManagerImpl
    ): CachingManager
}