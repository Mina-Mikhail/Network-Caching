package com.minaMikhail.preferences.rateLimiter.di

import com.minaMikhail.preferences.rateLimiter.RateLimiter
import com.minaMikhail.preferences.rateLimiter.RateLimiterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
fun interface RateLimiterModule {

    @Binds
    @Singleton
    fun bindsRateLimiterPreferences(
        rateLimiterPreferencesImpl: RateLimiterImpl
    ): RateLimiter
}