package com.example.mycafeapp.view

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.mycafeapp.R
import com.google.android.material.progressindicator.LinearProgressIndicator

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val progressBar: LinearProgressIndicator = findViewById(R.id.progress_bar)

        val animator = ValueAnimator.ofInt(0, 100)
        animator.duration = 3000L
        animator.addUpdateListener { animation ->
            progressBar.progress = animation.animatedValue as Int
        }
        animator.start()

        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000L)
    }
}