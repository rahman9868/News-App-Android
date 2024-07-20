package com.example.mycafeapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mycafeapp.R
import kotlinx.android.synthetic.main.activity_profile.nav_back

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        nav_back.setOnClickListener { finish() }
    }
}