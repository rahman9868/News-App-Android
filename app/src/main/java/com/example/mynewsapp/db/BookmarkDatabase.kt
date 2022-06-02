package com.example.mynewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynewsapp.entity.Bookmark

@Database(entities = [Bookmark::class], version = 1)
abstract class BookmarkDatabase(): RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao
    companion object{
        @Volatile
        private var INSTANCE : BookmarkDatabase? = null

        fun getInstance(context: Context) : BookmarkDatabase{
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookmarkDatabase::class.java,
                        "bookmark.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }
    }
}