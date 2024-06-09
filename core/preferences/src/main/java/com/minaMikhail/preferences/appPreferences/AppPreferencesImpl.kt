package com.minaMikhail.preferences.appPreferences

import android.content.SharedPreferences
import com.minaMikhail.preferences.appPreferences.enums.AppPreferencesKey
import com.minaMikhail.preferences.di.UserSessionSharedPref
import com.minaMikhail.preferences.utils.clear
import com.minaMikhail.preferences.utils.get
import com.minaMikhail.preferences.utils.removePref
import com.minaMikhail.preferences.utils.set
import javax.inject.Inject

class AppPreferencesImpl @Inject constructor(
    @UserSessionSharedPref private val sharedPreferences: SharedPreferences
) : AppPreferences {

    override fun enableCaching(enable: Boolean) {
        sharedPreferences[AppPreferencesKey.API_CACHING.name] = enable
    }

    override fun isCachingEnabled(): Boolean =
        sharedPreferences[AppPreferencesKey.API_CACHING.name, false]

    override fun clearPref(key: String) {
        sharedPreferences.removePref(key)
    }

    override fun clearUserData() {
        sharedPreferences.clear()
    }
}