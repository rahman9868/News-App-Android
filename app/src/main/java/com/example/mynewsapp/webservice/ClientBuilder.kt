package com.example.mynewsapp.webservice

import android.app.Application
import android.util.Log
import com.example.mynewsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ClientBuilder: Application() {
    private val baseUrl = "https://newsapi.org/v2/"
    private val apiKey = "6e1560e5f92140e897c357e578481a16"


    private val client = OkHttpClient().newBuilder()
        .addApiKeyInterceptor()
        .addLoggingInterceptor()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val service: ApiServices = retrofit.create(ApiServices::class.java)

    private fun OkHttpClient.Builder.addLoggingInterceptor(): OkHttpClient.Builder {
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor { Log.v("NewsApp", it) }
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(interceptor)
        }

        return this
    }

    private fun OkHttpClient.Builder.addApiKeyInterceptor(): OkHttpClient.Builder {

        val interceptor = Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder().addQueryParameter("apiKey", apiKey).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        addInterceptor(interceptor)
        return this
    }
}