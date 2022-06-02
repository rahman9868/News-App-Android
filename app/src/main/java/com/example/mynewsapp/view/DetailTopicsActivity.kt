package com.example.mynewsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapp.util.NewsUtil
import com.example.mynewsapp.R
import com.example.mynewsapp.adapter.HeadlinesRecyclerViewAdapter
import com.example.mynewsapp.entity.NewsHeadlines
import com.example.mynewsapp.entity.SuggestedTopics
import com.example.mynewsapp.util.CategoryNews
import com.example.mynewsapp.util.Country
import com.example.mynewsapp.response.Status
import com.example.mynewsapp.util.RoomUtil
import com.example.mynewsapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail_topics.*

class DetailTopicsActivity : AppCompatActivity() {

    var topics : SuggestedTopics? = null
    lateinit var mainModel : MainViewModel
    lateinit var adapter : HeadlinesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_topics)
        initiateViewModel()
        initiateAdapter()
        getDataTopics()
        getListNewsOfTopics()
    }

    override fun onResume() {
        getListNewsOfTopics()
        super.onResume()
    }

    private fun initiateAdapter() {
        adapter = HeadlinesRecyclerViewAdapter()
        headlines_recycler_view.adapter = adapter
        headlines_recycler_view.layoutManager = LinearLayoutManager(this)
    }

    private fun getListNewsOfTopics() {
        val title = topics?.title
        tv_page_title.text = title

        var category = ""
        when (title){
            "Entertainment" -> {
                category = CategoryNews.ENTERTAINMENT.value
                getPreferenceTopic(category)
            }
            "Technology" -> {
                category = CategoryNews.TECHNOLOGY.value
                getPreferenceTopic(category)
            }
            "Business" -> {
                category = CategoryNews.BUSINESS.value
                getPreferenceTopic(category)
            }
            "Sports" -> {
                category = CategoryNews.SPORTS.value
                getPreferenceTopic(category)
            }
            "Medical" -> {
                category = CategoryNews.HEALTH.value
                getPreferenceTopic(category)
            }
            "Science" -> {
                category = CategoryNews.SCIENCE.value
            }
            "International" -> getInternationalNews()
            "Bookmarks" -> getBookmark()
            else -> getArticles()
        }
    }

    private fun getArticles() {
        tv_page_title.text = intent?.getStringExtra("name")
        val category = CategoryNews.TECHNOLOGY.value
        mainModel.getPreference(Country.ID.value,category).observe(this, Observer {
            if(it.status == Status.SUCCESS) {
                val listHeadlines = NewsUtil.convertListArticleToListHeadLines(it.data?.articles)
                no_data.visibility = if(listHeadlines.isEmpty())  View.VISIBLE else View.GONE
                adapter.setData(listHeadlines)
            }
        })
    }

    private fun getBookmark() {
        RoomUtil.getBookmarks(this).observe(this, Observer { data ->
            val  listNews = mutableListOf<NewsHeadlines>()
            data.forEach {
                listNews.add(it.toNewsHeadlines())
            }
            listNews.reverse()

            no_data.visibility = if(listNews.isEmpty()) View.VISIBLE else View.GONE
            adapter.setData(listNews)
        })
    }

    private fun getPreferenceTopic(category: String) {
        mainModel.getPreference(Country.ID.value, category).observe(this, Observer {
            if(it.status == Status.SUCCESS) {
                val listHeadlines = NewsUtil.convertListArticleToListHeadLines(it.data?.articles)
                no_data.visibility = if(listHeadlines.isEmpty())  View.VISIBLE else View.GONE
                adapter.setData(listHeadlines)
            }
        })
    }

    private fun getInternationalNews() {
        mainModel.getInternationalNews().observe(this, Observer {
            if(it.status == Status.SUCCESS) {
                val listHeadlines = NewsUtil.convertListArticleToListHeadLines(it.data?.articles)
                no_data.visibility = if(listHeadlines.isEmpty())  View.VISIBLE else View.GONE
                adapter.setData(listHeadlines)
            }
        })
    }

    private fun initiateViewModel() {
        mainModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)
    }

    private fun getDataTopics() {
        topics = intent.getParcelableExtra(DETAIL_KEY)
    }



    companion object{
        val DETAIL_KEY = "Detail Key"
    }
}