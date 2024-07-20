package com.example.mycafeapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycafeapp.R
import com.example.mycafeapp.adapter.CafeViewAdapter
import com.example.mycafeapp.util.DummyUtil
import kotlinx.android.synthetic.main.activity_category_list.rvListCafe
import kotlinx.android.synthetic.main.activity_category_list.viewProfile

class CategoryListActivity : AppCompatActivity() {
    lateinit var adapter: CafeViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        getListCafe()
        viewProfile()
    }

    private fun viewProfile() {
        viewProfile.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
    }

    private fun getListCafe(){
        val list = DummyUtil.getListCafe()
        adapter = CafeViewAdapter()
        adapter.setData(list)
        rvListCafe.adapter = adapter
        rvListCafe.layoutManager = LinearLayoutManager(this)
    }

    companion object{
        val DETAIL_KEY = "DETAIL_KEY"
    }
}