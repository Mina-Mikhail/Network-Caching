package com.minaMikhail.preferences.rateLimiter

import androidx.datastore.preferences.core.Preferences

interface RateLimiter {

    suspend fun shouldFetch(
        key: Preferences.Key<Long>,
        cacheDurationSeconds: Long
    ): Boolean

    suspend fun fetched(key: Preferences.Key<Long>)

    suspend fun reset(key: Preferences.Key<Long>)

    suspend fun resetAll()
}