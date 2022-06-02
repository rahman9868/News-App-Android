package com.example.mynewsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynewsapp.entity.ArticlesModel
import com.example.mynewsapp.response.Response
import com.example.mynewsapp.webservice.ClientBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(): ViewModel() {

    val service = ClientBuilder().service

    fun getDataTopHeadlines(country: String): LiveData<Response<ArticlesModel>>{

        val listNews = MutableLiveData<Response<ArticlesModel>>()

        service.getTopHeadlines(country).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ listNews.value = Response.showLoading(null) }
            .doFinally{ listNews.value = Response.hideLoading(null) }
            .subscribeBy(
                onSuccess = { listNews.postValue(Response.success(it)) },
                onError = { listNews.postValue(Response.error(it.message.toString(),null)) }
            )
        return listNews

    }

    fun getPreference(country: String, category: String): LiveData<Response<ArticlesModel>>{
        val listNews = MutableLiveData<Response<ArticlesModel>>()

        service.getPreferences(category,country).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ listNews.value = Response.showLoading(null) }
            .doFinally{ listNews.value = Response.hideLoading(null) }
            .subscribeBy(
                onSuccess = { listNews.postValue(Response.success(it)) },
                onError = { listNews.postValue(Response.error(it.message.toString(),null)) }
            )
        return listNews
    }

    fun getInternationalNews(): LiveData<Response<ArticlesModel>>{
        val listNewsInternational = MutableLiveData<Response<ArticlesModel>>()

        service.getInternational().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ listNewsInternational.value = Response.showLoading(null) }
            .doFinally{ listNewsInternational.value = Response.hideLoading(null) }
            .subscribeBy(
                onSuccess = { listNewsInternational.postValue(Response.success(it)) },
                onError = { listNewsInternational.postValue(Response.error(it.message.toString(),null)) }
            )
        return listNewsInternational
    }

}