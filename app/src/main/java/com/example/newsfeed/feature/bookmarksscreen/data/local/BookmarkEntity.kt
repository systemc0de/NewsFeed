package com.example.newsfeed.feature.bookmarksscreen.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsfeed.feature.bookmarksscreen.data.local.BookmarkEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class BookmarkEntity(
    @PrimaryKey @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "image") val imageUrl: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "publishedAt") val publishedAt: String,
    @ColumnInfo(name = "isBookmarked") val isBookmarked: Boolean
) {
    companion object {
        const val TABLE_NAME = "BOOKMARKS_TABLE"
    }
}