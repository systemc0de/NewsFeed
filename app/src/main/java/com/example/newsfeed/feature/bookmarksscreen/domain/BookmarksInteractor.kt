package com.example.newsfeed.feature.bookmarksscreen.domain

import androidx.lifecycle.LiveData
import com.example.newsfeed.base.attempt
import com.example.newsfeed.feature.bookmarksscreen.data.BookmarksRepository
import com.example.newsfeed.feature.mainscreen.domain.model.NewsDomainModel

class BookmarksInteractor(private val repo: BookmarksRepository) {
    suspend fun create(article: NewsDomainModel) = repo.create(article)
    suspend fun read() = attempt { repo.read() }
    suspend fun update(article: NewsDomainModel) = repo.update(article)
    suspend fun delete(article: NewsDomainModel) = attempt {
        repo.delete(article)
    }
    suspend fun subscribeByDesc(): LiveData<List<NewsDomainModel>> = repo.subscribeByDesc()
}