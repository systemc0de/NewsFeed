package com.example.newsfeed.data

import com.example.newsfeed.data.api.model.NewsArticleModel
import com.example.newsfeed.feature.mainscreen.domain.model.NewsDomainModel

fun NewsArticleModel.toDomain(): NewsDomainModel =
    NewsDomainModel(
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt
    )