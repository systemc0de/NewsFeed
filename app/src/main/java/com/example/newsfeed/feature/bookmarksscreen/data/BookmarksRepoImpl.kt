package com.example.newsfeed.feature.bookmarksscreen.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.newsfeed.feature.bookmarksscreen.data.local.BookmarkDAO
import com.example.newsfeed.feature.mainscreen.domain.model.NewsDomainModel

class BookmarksRepoImpl(private val dao: BookmarkDAO) : BookmarksRepository {
    override suspend fun create(article: NewsDomainModel) {
        dao.create(article.toEntity())
    }

    override suspend fun read(): List<NewsDomainModel> {
        return dao.read().map { it.toDomain() }
    }

    override suspend fun update(article: NewsDomainModel) {
        dao.update(article.toEntity())
    }

    override suspend fun delete(article: NewsDomainModel) {
        dao.delete(article.toEntity())
    }

    override suspend fun subscribeByDesc(): LiveData<List<NewsDomainModel>> {
        return dao.subscribeByDesc().map { it -> it.map { it.toDomain() } }
    }
}