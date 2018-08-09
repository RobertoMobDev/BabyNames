package com.babynames.babynames

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.babynames.login.presentation.WelcomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }

}