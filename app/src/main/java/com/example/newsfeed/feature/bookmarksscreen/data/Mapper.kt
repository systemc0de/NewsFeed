package com.example.newsfeed.feature.bookmarksscreen.data

import com.example.newsfeed.feature.bookmarksscreen.data.local.BookmarkEntity
import com.example.newsfeed.feature.mainscreen.domain.model.NewsDomainModel

fun NewsDomainModel.toEntity(): BookmarkEntity = BookmarkEntity(
    url = url,
    imageUrl = urlToImage ?: "",
    author = author ?: "",
    title = title,
    description = description ?: "",
    publishedAt = publishedAt,
    isBookmarked = isBookmarked
)

fun BookmarkEntity.toDomain(): NewsDomainModel = NewsDomainModel(
    url = url,
    author = author,
    title = title,
    description = description,
    publishedAt = publishedAt,
    urlToImage = imageUrl,
    isBookmarked = isBookmarked
)