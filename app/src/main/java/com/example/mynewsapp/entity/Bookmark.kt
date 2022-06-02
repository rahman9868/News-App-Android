package com.example.mynewsapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var author: String? = "",
    var name: String? = "",
    var title: String? = "",
    var description: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    var publishedAt: String? = "",
    var content: String? = ""
){
    fun toNewsHeadlines(): NewsHeadlines {
        return NewsHeadlines(
            author, id.toString(), name,
            title, description, url, urlToImage,
            publishedAt, content
        )
    }
}