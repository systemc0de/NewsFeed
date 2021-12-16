package com.example.newsfeed.data.api.model

import com.google.gson.annotations.SerializedName

data class NewsArticleModel(
    @SerializedName("source")
    val source: NewsSourceModel,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("content")
    val content: String?
)

