package com.example.mynewsapp.webservice

import com.example.mynewsapp.entity.Articles
import com.example.mynewsapp.entity.ArticlesModel
import com.example.mynewsapp.response.Response
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country")country: String,
        @Query("pageSize")pageSize: Int = 100
    ) : Single<ArticlesModel>

    @GET("top-headlines")
    fun getPreferences(
        @Query("category")category: String,
        @Query("country")country: String,
        @Query("pageSize")pageSize: Int = 100
    ) : Single<ArticlesModel>

    @GET("everything")
    fun getInternational(
        @Query("language")language: String = "en",
        @Query("q")q: String = "international",
        @Query("pageSize")pageSize: Int = 100
    ) : Single<ArticlesModel>
}