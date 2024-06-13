package com.minaMikhail.preferences.cachingManager

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface CachingManager {

    suspend fun <T> set(
        key: Preferences.Key<T>,
        value: T
    )

    suspend fun <T> get(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T>

    suspend fun <T> getFirst(
        key: Preferences.Key<T>,
        defaultValue: T
    ): T

    suspend fun <T> remove(key: Preferences.Key<T>)

    suspend fun clearAll()
}