package com.minaMikhail.preferences.appPreferences

interface AppPreferences {

    fun enableCaching(enable: Boolean)

    fun isCachingEnabled(): Boolean

    fun clearPref(key : String)

    fun clearUserData()
}