package com.minaMikhail.preferences.dataStore.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException

suspend fun <T> DataStore<Preferences>.set(
    key: Preferences.Key<T>,
    value: T
) {
    edit { preferences -> preferences[key] = value }
}

suspend inline fun <T> DataStore<Preferences>.get(
    key: Preferences.Key<T>,
    defaultValue: T
): Flow<T> {
    return data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[key] ?: defaultValue
    }
}

suspend inline fun <T> DataStore<Preferences>.getFirst(
    key: Preferences.Key<T>,
    defaultValue: T
): T {
    return data.firstOrNull()?.get(key) ?: defaultValue
}

suspend fun <T> DataStore<Preferences>.remove(key: Preferences.Key<T>) {
    edit { preferences -> preferences.remove(key) }
}

suspend fun DataStore<Preferences>.clearAll() {
    edit { preferences -> preferences.clear() }
}