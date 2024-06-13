package com.minaMikhail.preferences.dataStore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @DataStoreIoDispatcher
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @DataStoreIoScope
    fun providesIoCoroutineScope(
        @DataStoreIoDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @Provides
    @Singleton
    @UserSessionDataStore
    fun provideUserSessionDataStore(
        @ApplicationContext applicationContext: Context,
        @DataStoreIoScope ioScope: CoroutineScope
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = ioScope,
        produceFile = { applicationContext.filesDir.resolve("user_session.preferences_pb") }
    )

    @Provides
    @Singleton
    @RateLimiterDataStore
    fun provideRateLimiterDataStore(
        @ApplicationContext applicationContext: Context,
        @DataStoreIoScope ioScope: CoroutineScope
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = ioScope,
        produceFile = { applicationContext.filesDir.resolve("rale_limiter.preferences_pb") }
    )
}