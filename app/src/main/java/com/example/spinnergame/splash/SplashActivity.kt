package com.example.spinnergame.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spinnergame.MainActivity
import com.example.spinnergame.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)

    }
}