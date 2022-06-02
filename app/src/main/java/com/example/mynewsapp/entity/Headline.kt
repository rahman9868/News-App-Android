package com.example.mynewsapp.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsHeadlines(
    var author : String?,
    var id : String?,
    var name : String?,
    var title : String?,
    var description : String?,
    var url : String?,
    var urlToImage : String?,
    var publishedAt : String?,
    var content : String?
) : Parcelable {

    fun convertToBookmark(): Bookmark{
        val bookmark = Bookmark(
            0,
            author,
            author,
            title,
            description,
            url,
            urlToImage,
            publishedAt,
            content
        )
        return bookmark
    }
}