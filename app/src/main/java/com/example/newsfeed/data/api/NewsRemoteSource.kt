package com.example.newsfeed.data.api

import com.example.newsfeed.data.api.NewsApi
import com.example.newsfeed.data.api.model.NewsModel

class NewsRemoteSource(private val api: NewsApi) {
    suspend fun fetchNews(): NewsModel = api.fetchNews()
}