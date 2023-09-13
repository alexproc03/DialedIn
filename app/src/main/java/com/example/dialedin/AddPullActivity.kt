package com.example.dialedin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toolbar

class AddPullActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_pull_activity)

        //addPullToolbar functionality
        val addPullToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.addPullToolbar)
        setSupportActionBar(addPullToolbar)

        //ratingSeekbar functionality
        val ratingSeekBar: SeekBar = findViewById(R.id.ratingSeekBar)
        var ratingDisplay: TextView = findViewById(R.id.ratingDisplay)
        ratingSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                ratingDisplay.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


    }
}