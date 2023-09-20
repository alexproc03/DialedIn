package com.alexprosd.dialedin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.dialedin.R
import com.example.dialedin.databinding.AddPullActivityBinding
import com.alexprosd.dialedin.entrydata.Entry
import com.alexprosd.dialedin.entrydata.EntryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AddPullActivity : AppCompatActivity() {

    private lateinit var binding: AddPullActivityBinding
    private lateinit var entryDb : EntryDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddPullActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        entryDb = EntryDatabase.getDatabase(this)

        //addPullToolbar functionality
        val addPullToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.addPullToolbar)
        setSupportActionBar(addPullToolbar)
        supportActionBar?.title = "Add Pull"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //beanSpinner functionality
        val beanSpinner: Spinner = findViewById(R.id.beanSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.roast_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            beanSpinner.adapter = adapter
        }

        //ratingSeekbar functionality
        val ratingSeekBar: SeekBar = findViewById(R.id.ratingSeekBar)
        var ratingDisplay: TextView = findViewById(R.id.ratingDisplay)
        ratingSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                ratingDisplay.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        //Save Button functionality
        binding.addPullToolbar.saveButton.setOnClickListener {
            writeData()
        }

    }

    private fun writeData() {

        val title: String = binding.shotTitle.text.toString()
        val bean: String = binding.beanSpinner.selectedItem.toString()
        val grindSize: String = binding.grindInput.text.toString()
        val dose: String = binding.doseInput.text.toString()
        val yield: String = binding.yieldInput.text.toString()
        val preInfuse: String = binding.preinfusionInput.text.toString()
        val time: String = binding.timeInput.text.toString()
        val notes: String = binding.shotNotesInput.text.toString()
        val rating: Int = binding.ratingSeekBar.progress
        val favorite: Boolean = binding.favoriteShotCheckBox.isActivated

        if (title.isNotEmpty() && grindSize.isNotEmpty() && dose.isNotEmpty() && yield.isNotEmpty() && preInfuse.isNotEmpty() && time.isNotEmpty() && notes.isNotEmpty()) {
            val entry = Entry(
                null, title, bean, grindSize.toDouble(), dose.toDouble(), yield.toDouble(), preInfuse.toDouble(), time.toDouble(), notes, rating, favorite
            )
            GlobalScope.launch(Dispatchers.IO) {

                entryDb.EntryDao().upsertEntry(entry)

            }
            Toast.makeText(this@AddPullActivity, "Added shot",Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else {
            Toast.makeText(this@AddPullActivity, "Please fill all parameters",Toast.LENGTH_SHORT).show()
        }
    }
}


