package com.example.newsfeed.feature.bookmarksscreen.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookmarkDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(entity: BookmarkEntity)

    @Query("SELECT * FROM ${BookmarkEntity.TABLE_NAME}")
    suspend fun read(): List<BookmarkEntity>

    @Query("SELECT * FROM ${BookmarkEntity.TABLE_NAME} ORDER BY url DESC")
    fun subscribeByDesc(): LiveData<List<BookmarkEntity>>

    @Update
    suspend fun update(entity: BookmarkEntity)

    @Delete
    suspend fun delete(entity: BookmarkEntity)
}