package com.example.dialedin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mainToolbar functionality
        val mainToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.mainToolbar)
        setSupportActionBar(mainToolbar)
        supportActionBar?.title = "DialedIn"



        val addShotButton: FloatingActionButton = findViewById(R.id.addShotButton)
        addShotButton.setOnClickListener {
            val intent = Intent(this, AddPullActivity::class.java)
            startActivity(intent)
        }

    }
}