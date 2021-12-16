package com.example.newsfeed.data.api

import com.example.newsfeed.data.toDomain
import com.example.newsfeed.feature.mainscreen.domain.model.NewsDomainModel

class NewsRepositoryImpl(private val source: NewsRemoteSource) : NewsRepository {
    override suspend fun fetchNews(): List<NewsDomainModel> {
        return source.fetchNews().articles.map { article -> article.toDomain() }
    }
}