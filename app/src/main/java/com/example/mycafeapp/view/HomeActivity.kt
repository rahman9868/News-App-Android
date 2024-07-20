package com.example.mycafeapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mycafeapp.R
import com.example.mycafeapp.adapter.CategoryViewAdapter
import com.example.mycafeapp.util.DummyUtil
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*

class HomeActivity : AppCompatActivity() {
    lateinit var adapterCategory: CategoryViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getCategory()
        getRecommendedPlace()
        viewProfile()
    }

    private fun viewProfile() {
        viewProfile.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
    }

    private fun getCategory() {
        val categories = DummyUtil.getCategory()
        adapterCategory = CategoryViewAdapter()
        adapterCategory.setData(categories)
        rvCategory.adapter = adapterCategory

    }

    @SuppressLint("SetTextI18n")
    private fun getRecommendedPlace() {
        val recommended = DummyUtil.recommendedCafe()
        ivRecommended.setImageResource(recommended.picture)
        txtName.text = recommended.name + " - " + recommended.location
        txtLocation.text = recommended.address
        txtTime.text = recommended.openTime +" - "+ recommended.closeTime
        txtRate.text = recommended.rate.toString()
        txtReviews.text = recommended.countRate.toString()
        txtRange.text = recommended.rangeLocation
    }
}