package com.example.newsfeed.data.api.model

import com.example.newsfeed.data.api.model.NewsArticleModel
import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("totalResults")
    val totalResults: Long,
    @SerializedName("articles")
    val articles: List<NewsArticleModel>
)

