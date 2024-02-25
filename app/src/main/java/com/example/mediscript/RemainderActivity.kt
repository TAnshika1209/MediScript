package com.example.mediscript

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RemainderActivity : AppCompatActivity() {

    lateinit var createRem : FloatingActionButton
    lateinit var rvCon : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)
        createRem.setOnClickListener{



        }
    }
}
