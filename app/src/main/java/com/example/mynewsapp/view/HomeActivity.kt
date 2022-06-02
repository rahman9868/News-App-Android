package com.example.mynewsapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mynewsapp.util.NewsUtil
import com.example.mynewsapp.R
import com.example.mynewsapp.adapter.PreferencesViewPagerAdapter
import com.example.mynewsapp.adapter.SuggestedTopicsRecyclerViewAdapter
import com.example.mynewsapp.adapter.TopStoriesHomeRecyclerViewAdapter
import com.example.mynewsapp.entity.SuggestedTopics
import com.example.mynewsapp.util.CategoryNews
import com.example.mynewsapp.util.Country
import com.example.mynewsapp.response.Status
import com.example.mynewsapp.viewmodel.MainViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var adapterStories : TopStoriesHomeRecyclerViewAdapter
    lateinit var adapterTopics: SuggestedTopicsRecyclerViewAdapter
    lateinit var mainModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initiateViewModel()
        initiateAdapter()
        checkAccount()
        getDataStories("id")
        getDataTopics()
        getDataPreference()
        view_all_top_stories.setOnClickListener { showFullStories() }
        viewBookmarks.setOnClickListener { showBookmarks() }
        img_user.setOnClickListener { showProfile() }
    }

    private fun showProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    private fun checkAccount() {
        val account = GoogleSignIn.getLastSignedInAccount(this)
        Log.v("ADX","Account "+account.photoUrl)
        if(account != null){
            Glide.with(this).load(account.photoUrl).into(img_user)
            welcomeTextView.text = "Hi "+account.displayName

        }
    }

    private fun initiateAdapter() {
        adapterStories = TopStoriesHomeRecyclerViewAdapter()
        top_stories_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        top_stories_recycler_view.adapter = adapterStories

        adapterTopics = SuggestedTopicsRecyclerViewAdapter()
        suggested_topics_recycler_view.layoutManager = GridLayoutManager(this, 3)
        suggested_topics_recycler_view.adapter = adapterTopics

    }

    private fun getDataStories(country: String) {
        val category = CategoryNews.TECHNOLOGY.value
        mainModel.getPreference(country,category).observe(this@HomeActivity, Observer {
            Log.v("ADX","getDataTopHeadlines: "+ Gson().toJson(it))

            if(it.status == Status.SUCCESS) {
                val listStories = NewsUtil.convertListArticleToListHeadLines(it.data?.articles)
                adapterStories.setData(listStories)
            }

        })
    }

    private fun getDataTopics(){
        val listTopics = NewsUtil.getListTopics()
        adapterTopics.setData(listTopics)
    }

    private fun getDataPreference() {
        val country = Country.ID.value
        mainModel.getDataTopHeadlines(country).observe(this@HomeActivity, Observer {
            Log.v("ADX","getDataPreference: "+ Gson().toJson(it))

            if(it.status == Status.SUCCESS) {
                val listPreference = NewsUtil.convertListArticleToListHeadLines(it.data?.articles)
                home_view_pager.adapter = PreferencesViewPagerAdapter(this, listPreference)
            }
        })
    }

    private fun showFullStories(){
        val intent = Intent(this, DetailTopicsActivity::class.java)
        intent.putExtra("name", "Top Headlines")
        startActivity(intent)
    }

    private fun showBookmarks() {
        val intent = Intent(this, DetailTopicsActivity::class.java)
        val data = SuggestedTopics(0,"Bookmarks")
        intent.putExtra(DetailTopicsActivity.DETAIL_KEY, data)
        startActivity(intent)
    }

    private fun initiateViewModel() {
        mainModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)
    }
}