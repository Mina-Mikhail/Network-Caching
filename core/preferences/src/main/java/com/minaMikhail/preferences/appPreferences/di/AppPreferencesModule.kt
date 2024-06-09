package com.minaMikhail.preferences.appPreferences.di

import com.minaMikhail.preferences.appPreferences.AppPreferences
import com.minaMikhail.preferences.appPreferences.AppPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppPreferencesModule {

    @Binds
    @Singleton
    fun bindAppPreferences(
        appPreferencesImpl: AppPreferencesImpl
    ): AppPreferences
}