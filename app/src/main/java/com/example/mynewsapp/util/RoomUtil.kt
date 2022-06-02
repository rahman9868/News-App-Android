package com.example.mynewsapp.util

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.RoomDatabase
import com.example.mynewsapp.db.BookmarkDatabase
import com.example.mynewsapp.entity.Bookmark

object RoomUtil {

    fun insertBookmark(context: Context,data: Bookmark){
        val db = BookmarkDatabase.getInstance(context).bookmarkDao()
        db.addBookmark(data)
    }

    fun getBookmarks(context: Context): LiveData<List<Bookmark>>{
        val db = BookmarkDatabase.getInstance(context).bookmarkDao()
        return db.getBookmarks()
    }

    fun removeBookmark(context: Context,url: String){
        val db = BookmarkDatabase.getInstance(context).bookmarkDao()
        db.removeBookmark(url)
    }

    fun getBookmarkByUrl(context: Context,url: String): List<Bookmark>{
        val db = BookmarkDatabase.getInstance(context).bookmarkDao()
        return db.checkBookmark(url)
    }
}