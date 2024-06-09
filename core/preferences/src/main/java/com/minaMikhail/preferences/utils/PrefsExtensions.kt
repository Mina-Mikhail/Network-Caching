package com.minaMikhail.preferences.utils

import android.content.SharedPreferences

operator fun <T : Any> SharedPreferences.set(key: String, value: T?) {
    when (value) {
        is String -> editPref { it.putString(key, value) }
        is Int -> editPref { it.putInt(key, value) }
        is Boolean -> editPref { it.putBoolean(key, value) }
        is Float -> editPref { it.putFloat(key, value) }
        is Long -> editPref { it.putLong(key, value) }

        else -> throw UnsupportedOperationException(
            "$key is: ${value?.javaClass}, This data type not yet implemented in: ${this.javaClass}"
        )
    }
}

inline operator fun <reified T : Any> SharedPreferences.get(
    key: String,
    defaultValue: T
): T {
    return when (defaultValue) {
        is String -> getString(key, defaultValue as String) as T
        is Int -> getInt(key, defaultValue as Int) as T
        is Boolean -> getBoolean(key, defaultValue as Boolean) as T
        is Float -> getFloat(key, defaultValue as Float) as T
        is Long -> getLong(key, defaultValue as Long) as T

        else -> throw UnsupportedOperationException(
            "$key is: ${defaultValue.javaClass}, This data type not yet implemented in: ${this.javaClass}"
        )
    }
}

inline fun SharedPreferences.editPref(operation: (SharedPreferences.Editor) -> Unit) {
    with(this.edit()) {
        operation(this)
        apply()
    }
}

fun SharedPreferences.removePref(key: String) {
    with(this.edit()) {
        remove(key)
        apply()
    }
}

fun SharedPreferences.clear() {
    with(this.edit()) {
        clear()
        apply()
    }
}