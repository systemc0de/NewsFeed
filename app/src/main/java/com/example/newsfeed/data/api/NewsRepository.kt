package com.example.newsfeed.data.api

import com.example.newsfeed.feature.mainscreen.domain.model.NewsDomainModel

interface NewsRepository {
    suspend fun fetchNews(): List<NewsDomainModel>
}