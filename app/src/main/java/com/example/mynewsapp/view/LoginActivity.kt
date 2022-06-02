package com.example.mynewsapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mynewsapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val RC_SIGN_IN = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupGoogleSignIn()
        btnSignIn.setOnClickListener {
            signIn()
        }
    }

    private fun setupGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        checkAccount()
    }

    private fun checkAccount() {
        val account = GoogleSignIn.getLastSignedInAccount(this)
        Log.v("ADX","Account "+account)
        if(account != null){
            goToNews()
        }
    }

    @Suppress("DEPRECATION")
    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.v("ADX","requestCode $requestCode")
        when(requestCode){
            RC_SIGN_IN -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                loginAccount(task)
            }
        }
    }

    private fun loginAccount(task: Task<GoogleSignInAccount>?) {
        try {
            val account: GoogleSignInAccount? = task?.getResult(ApiException::class.java)
            Log.v("ADX", "SignIn ${account?.email}")
            val password = account?.id
            if(account != null)goToNews()
        }catch (e: Exception){
            Log.v("ADX","Failed Login ${e.message}")
        }

    }

    private fun goToNews() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}