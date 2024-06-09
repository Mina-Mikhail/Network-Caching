package com.minaMikhail.preferences.rateLimiterPreferences

import com.minaMikhail.preferences.rateLimiterPreferences.enums.RateLimiterPreferencesKey

interface RateLimiterPreferences {

    fun shouldFetch(
        key: RateLimiterPreferencesKey,
        cacheDurationSeconds: Long
    ): Boolean

    fun fetched(key: RateLimiterPreferencesKey)

    fun reset(key: RateLimiterPreferencesKey)

    fun resetAll()
}