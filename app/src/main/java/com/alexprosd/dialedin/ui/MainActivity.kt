package com.alexprosd.dialedin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alexprosd.dialedin.entrydata.Entry
import com.example.dialedin.R
import com.example.dialedin.databinding.ActivityMainBinding
import com.alexprosd.dialedin.entrydata.EntryDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var entryDb : EntryDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        entryDb = EntryDatabase.getDatabase(this)

        //mainToolbar functionality
        val mainToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.mainToolbar)
        setSupportActionBar(mainToolbar)
        supportActionBar?.title = "DialedIn"

        val addShotButton: FloatingActionButton = binding.addShotButton
        addShotButton.setOnClickListener {
            val intent = Intent(this, AddPullActivity::class.java)
            startActivity(intent)
        }

        val lookUpButton = binding.lookUpButton
        lookUpButton.setOnClickListener() {
            readData()
        }
    }

    private fun readData() {
        val id = binding.idInput.text.toString()

        if (id.isNotEmpty()) {

            GlobalScope.launch {
                var entry : Entry = entryDb.EntryDao().findById(id.toInt())
                if (entry != null)
                    displayData(entry)
                else
                    binding.dataOutput.text = "entry does not exist"
            }
        }
        else {
            Toast.makeText(this@MainActivity, "Please input ID", Toast.LENGTH_SHORT).show()
        }

    }
    private suspend fun displayData(entry: Entry) {
        withContext(Dispatchers.Main) {
            binding.dataOutput.text = entry.title + "\n" + entry.bean + "\n" + entry.rating + "\n" + entry.favorite
        }
    }
}