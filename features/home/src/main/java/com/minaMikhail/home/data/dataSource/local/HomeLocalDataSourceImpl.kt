package com.minaMikhail.home.data.dataSource.local

import android.content.SharedPreferences
import com.minaMikhail.home.domain.model.HomeArticle
import com.minaMikhail.preferences.appPreferences.enums.AppPreferencesKey
import com.minaMikhail.preferences.di.UserSessionSharedPref
import com.minaMikhail.preferences.utils.get
import com.minaMikhail.preferences.utils.removePref
import com.minaMikhail.preferences.utils.set
import com.minaMikhail.utils.extensions.toJsonList
import com.minaMikhail.utils.extensions.toJsonString
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(
    @UserSessionSharedPref private val sharedPreferences: SharedPreferences
) : HomeLocalDataSource {

    override fun saveNews(news: List<HomeArticle>) {
        sharedPreferences[AppPreferencesKey.HOME_FEEDS.name] = news.take(4).toJsonString()
    }

    override fun getNews(): List<HomeArticle>? =
        (sharedPreferences[AppPreferencesKey.HOME_FEEDS.name, ""]).toJsonList<List<HomeArticle>>()

    override fun clearNews() {
        sharedPreferences.removePref(AppPreferencesKey.HOME_FEEDS.name)
    }
}