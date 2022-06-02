package com.example.mynewsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mynewsapp.util.Country
import com.example.mynewsapp.viewmodel.MainViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    lateinit var mainModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initiateViewModel()
        getDataTopHeadlines(Country.US.value)
    }

    private fun getDataTopHeadlines(country: String) {
        mainModel.getDataTopHeadlines(country).observe(this@MainActivity, Observer {
            Log.v("ADX","getDataTopHeadlines: "+ Gson().toJson(it))
        })
    }

    private fun initiateViewModel() {
        mainModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)
    }



}