package com.example.mediscript

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class OpeningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)
        Handler(Looper.getMainLooper()).postDelayed({
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        },1500)
    }

}
