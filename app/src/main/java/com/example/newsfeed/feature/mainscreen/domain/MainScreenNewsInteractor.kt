package com.example.newsfeed.feature.mainscreen.domain

import com.example.newsfeed.base.attempt
import com.example.newsfeed.base.mapToList
import com.example.newsfeed.data.api.NewsRepository
import com.example.newsfeed.feature.bookmarksscreen.data.BookmarksRepository

class MainScreenNewsInteractor(
    private val newsRepository: NewsRepository,
    private val bookmarksRepository: BookmarksRepository
) {
    suspend fun fetchNews() = attempt {
        val news = newsRepository.fetchNews()
        val bookmarks = bookmarksRepository.read()
        mapToList(oldList = news, newList = bookmarks)
    }
}