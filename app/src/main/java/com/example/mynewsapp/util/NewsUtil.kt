package com.example.mynewsapp.util

import android.util.Log
import com.example.mynewsapp.R
import com.example.mynewsapp.entity.Articles
import com.example.mynewsapp.entity.NewsHeadlines
import com.example.mynewsapp.entity.SuggestedTopics
import com.google.gson.Gson
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object NewsUtil {

    fun convertListArticleToListHeadLines(data: List<Articles>?): List<NewsHeadlines> {
        val listHeadlines = mutableListOf<NewsHeadlines>()
        data?.forEach {
            listHeadlines.add(
                NewsHeadlines(
                    it.author,
                    it.source.id,
                    it.source.name,
                    it.title,
                    it.description,
                    it.url,
                    it.urlToImage,
                    it.publishedAt,
                    it.content
                )
            )
        }
        Log.v("ADX","listHeadlines : "+ Gson().toJson(listHeadlines))
        return listHeadlines
    }

    fun getListTopics(): List<SuggestedTopics>{
        val list = mutableListOf<SuggestedTopics>()
        list.add(SuggestedTopics(R.drawable.ic_twitter, "Business"))
        list.add(SuggestedTopics(R.drawable.ic_facebook_100, "Entertainment"))
        list.add(SuggestedTopics(R.drawable.ic_icons8_instagram_100, "Sports"))
        list.add(SuggestedTopics(R.drawable.ic_icons8_linkedin_100, "Science"))
        list.add(SuggestedTopics(R.drawable.ic_icons8_pinterest_100, "Technology"))
        list.add(SuggestedTopics(R.drawable.ic_icons8_reddit_100, "Medical"))
        list.add(SuggestedTopics(R.drawable.ic_icons8_myspace_100, "International"))
        return list
    }

    fun convertStringDateToOtherFormat(dateTime: String?): String{
        if(dateTime.isNullOrEmpty()) return ""
        return try {
            val formater = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outPutFormat = DateTimeFormatter.ofPattern("MMM d,yyyy'|'HH:mm")
            LocalDateTime.parse(dateTime, formater).format(outPutFormat).toString()
        }catch (e: Exception){
            Log.v("CRX","Failed Convert Time "+e.message)
            ""
        }
    }
}