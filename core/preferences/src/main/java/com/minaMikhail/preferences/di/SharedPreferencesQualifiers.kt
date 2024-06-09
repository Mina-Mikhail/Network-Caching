package com.minaMikhail.preferences.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class UserSessionSharedPref

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RateLimiterSharedPref