package com.minaMikhail.preferences.cachingManager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.minaMikhail.preferences.dataStore.di.UserSessionDataStore
import com.minaMikhail.preferences.dataStore.utils.clearAll
import com.minaMikhail.preferences.dataStore.utils.get
import com.minaMikhail.preferences.dataStore.utils.getFirst
import com.minaMikhail.preferences.dataStore.utils.remove
import com.minaMikhail.preferences.dataStore.utils.set
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CachingManagerImpl @Inject constructor(
    @UserSessionDataStore private val dataStore: DataStore<Preferences>
) : CachingManager {

    override suspend fun <T> set(
        key: Preferences.Key<T>,
        value: T
    ) {
        dataStore.set(key, value)
    }

    override suspend fun <T> get(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return dataStore.get(key, defaultValue)
    }

    override suspend fun <T> getFirst(
        key: Preferences.Key<T>,
        defaultValue: T
    ): T {
        return dataStore.getFirst(key, defaultValue)
    }

    override suspend fun <T> remove(key: Preferences.Key<T>) {
        dataStore.remove(key)
    }

    override suspend fun clearAll() {
        dataStore.clearAll()
    }
}