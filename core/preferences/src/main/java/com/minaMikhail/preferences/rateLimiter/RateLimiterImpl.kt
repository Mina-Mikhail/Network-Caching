package com.minaMikhail.preferences.rateLimiter

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.minaMikhail.preferences.dataStore.di.RateLimiterDataStore
import com.minaMikhail.preferences.dataStore.utils.clearAll
import com.minaMikhail.preferences.dataStore.utils.getFirst
import com.minaMikhail.preferences.dataStore.utils.remove
import com.minaMikhail.preferences.dataStore.utils.set
import java.util.Calendar
import javax.inject.Inject

class RateLimiterImpl @Inject constructor(
    @RateLimiterDataStore private val dataStore: DataStore<Preferences>
) : RateLimiter {

    override suspend fun shouldFetch(
        key: Preferences.Key<Long>,
        cacheDurationSeconds: Long
    ): Boolean = Calendar.getInstance().time.time.minus(
        dataStore.getFirst(key, 0L)
    ) >= cacheDurationSeconds * 1000

    override suspend fun fetched(key: Preferences.Key<Long>) {
        dataStore.set(key, Calendar.getInstance().time.time)
    }

    override suspend fun reset(key: Preferences.Key<Long>) {
        dataStore.remove(key)
    }

    override suspend fun resetAll() {
        dataStore.clearAll()
    }
}