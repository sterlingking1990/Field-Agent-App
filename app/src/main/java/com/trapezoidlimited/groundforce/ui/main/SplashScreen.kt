package com.trapezoidlimited.groundforce.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}