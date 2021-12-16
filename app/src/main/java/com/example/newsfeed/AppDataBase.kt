package com.example.newsfeed

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsfeed.feature.bookmarksscreen.data.local.BookmarkDAO
import com.example.newsfeed.feature.bookmarksscreen.data.local.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 11)
abstract class AppDataBase : RoomDatabase() {
    abstract fun bookmarksDAO(): BookmarkDAO
}