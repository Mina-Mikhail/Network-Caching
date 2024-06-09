package com.minaMikhail.preferences.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    @UserSessionSharedPref
    fun provideUserSessionSharedPref(
        @ApplicationContext applicationContext: Context
    ): SharedPreferences = applicationContext.getSharedPreferences(
        "USER_SESSION_SHARED_PREFERENCE",
        Context.MODE_PRIVATE
    )

    @Provides
    @Singleton
    @RateLimiterSharedPref
    fun provideRateLimiterSharedPref(
        @ApplicationContext applicationContext: Context
    ): SharedPreferences = applicationContext.getSharedPreferences(
        "RATE_LIMITER_SHARED_PREFERENCE",
        Context.MODE_PRIVATE
    )
}