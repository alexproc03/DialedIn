package com.example.dialedin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddPullActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_pull_activity)

        //addPullToolbar functionality
        val addPullToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.addPullToolbar)
        setSupportActionBar(addPullToolbar)
        supportActionBar?.title = "Add Pull"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


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

        //Save Button Functionality
        val saveButton: FloatingActionButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //spinner dummy code
        val spinner: Spinner = findViewById(R.id.beanSpinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.roast_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


    }
}