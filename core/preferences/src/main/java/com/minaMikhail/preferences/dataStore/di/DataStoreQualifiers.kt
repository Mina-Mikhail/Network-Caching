package com.minaMikhail.preferences.dataStore.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class UserSessionDataStore

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RateLimiterDataStore

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DataStoreIoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DataStoreIoScope