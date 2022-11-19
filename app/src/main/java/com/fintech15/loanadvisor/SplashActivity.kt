package com.fintech15.loanadvisor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed( {
            val i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
            finish()
        },5000)
    }
}