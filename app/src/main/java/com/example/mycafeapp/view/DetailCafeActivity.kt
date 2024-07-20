package com.example.mycafeapp.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mycafeapp.R

class DetailCafeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_cafe)
    }

    companion object{
        val DETAIL_KEY = "Detail Key"
    }
}