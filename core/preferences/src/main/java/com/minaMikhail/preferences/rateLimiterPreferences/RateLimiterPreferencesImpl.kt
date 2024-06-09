package com.minaMikhail.preferences.rateLimiterPreferences

import android.content.SharedPreferences
import com.minaMikhail.preferences.di.RateLimiterSharedPref
import com.minaMikhail.preferences.rateLimiterPreferences.enums.RateLimiterPreferencesKey
import com.minaMikhail.preferences.utils.clear
import com.minaMikhail.preferences.utils.get
import com.minaMikhail.preferences.utils.removePref
import com.minaMikhail.preferences.utils.set
import java.util.Calendar
import javax.inject.Inject

class RateLimiterPreferencesImpl @Inject constructor(
    @RateLimiterSharedPref private val sharedPreferences: SharedPreferences
) : RateLimiterPreferences {

    override fun shouldFetch(
        key: RateLimiterPreferencesKey,
        cacheDurationSeconds: Long
    ): Boolean = Calendar.getInstance().time.time.minus(
        sharedPreferences[key.name, 0L]
    ) >= cacheDurationSeconds * 1000

    override fun fetched(key: RateLimiterPreferencesKey) {
        sharedPreferences[key.name] = Calendar.getInstance().time.time
    }

    override fun reset(key: RateLimiterPreferencesKey) {
        sharedPreferences.removePref(key.name)
    }

    override fun resetAll() {
        sharedPreferences.clear()
    }
}