package com.example.mycafeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycafeapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        startActivity(Intent(this, HomeActivity::class.java))
        if(etEmail.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty()){
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}