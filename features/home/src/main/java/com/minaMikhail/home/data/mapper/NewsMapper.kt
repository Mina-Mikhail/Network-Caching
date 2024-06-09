package com.minaMikhail.home.data.mapper

import com.minaMikhail.base.mappers.BaseMapper
import com.minaMikhail.home.data.response.NewsResponse
import com.minaMikhail.home.domain.model.HomeArticle
import javax.inject.Inject

class NewsMapper @Inject constructor() : BaseMapper<NewsResponse, List<HomeArticle>> {

    override fun map(from: NewsResponse): List<HomeArticle> {
        val articles = mutableListOf<HomeArticle>()

        from.articles?.forEach {
            articles.add(
                HomeArticle(
                    author = it.author,
                    content = it.content,
                    description = it.description,
                    publishedAt = it.publishedAt,
                    title = it.title,
                    url = it.url,
                    urlToImage = it.urlToImage,
                )
            )
        }

        return articles
    }
}