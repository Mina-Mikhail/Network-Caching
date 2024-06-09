package com.minaMikhail.preferences.rateLimiterPreferences.di

import com.minaMikhail.preferences.rateLimiterPreferences.RateLimiterPreferences
import com.minaMikhail.preferences.rateLimiterPreferences.RateLimiterPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RateLimiterPreferencesModule {

    @Binds
    @Singleton
    fun bindsRateLimiterPreferences(
        rateLimiterPreferencesImpl: RateLimiterPreferencesImpl
    ): RateLimiterPreferences
}