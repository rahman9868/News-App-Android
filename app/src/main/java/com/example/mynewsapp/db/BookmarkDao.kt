package com.example.mynewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mynewsapp.entity.Bookmark

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark")
    fun getBookmarks(): LiveData<List<Bookmark>>

    @Query("DELETE FROM bookmark WHERE url=:url")
    fun removeBookmark(url: String)

    @Query("SELECT * FROM bookmark WHERE url=:url")
    fun checkBookmark(url: String): List<Bookmark>
}
