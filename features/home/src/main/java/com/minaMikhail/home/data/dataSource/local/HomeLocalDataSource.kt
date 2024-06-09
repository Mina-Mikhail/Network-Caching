package com.minaMikhail.home.data.dataSource.local

import com.minaMikhail.home.domain.model.HomeArticle

interface HomeLocalDataSource {

    fun saveNews(news: List<HomeArticle>)

    fun getNews(): List<HomeArticle>?

    fun clearNews()
}