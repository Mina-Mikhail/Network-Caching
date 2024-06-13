package com.minaMikhail.preferences.cachingManager.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object CachingPreferenceKey {

    val REMOTE_CONFIG_CACHING_KEY = booleanPreferencesKey("ENABLE_CACHING")
    val NEWS_KEY = stringPreferencesKey("NEWS")

}