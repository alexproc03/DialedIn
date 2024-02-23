package com.alexprosd.dialedin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dialedin.R
import com.example.dialedin.databinding.ActivityMainBinding
import com.alexprosd.dialedin.entrydata.EntryDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
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

        val shotRecyclerView : RecyclerView = binding.shotRecyclerView
        shotRecyclerView.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch(Dispatchers.Main) {
            val entries = withContext(Dispatchers.IO) {
                entryDb.EntryDao().getAll()
            }
            shotRecyclerView.adapter = RecyclerAdapter(entries)

        }

        lifecycleScope.launch(Dispatchers.Main) {
            val entries = withContext(Dispatchers.IO) {
                entryDb.EntryDao().getAll()
            }
            val adapter = RecyclerAdapter(entries)
            shotRecyclerView.adapter = adapter
        }
        //mainToolbar functionality
        val mainToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.mainToolbar)
        setSupportActionBar(mainToolbar)
        supportActionBar?.title = "DialedIn"

        val addShotButton: FloatingActionButton = binding.addShotButton
        addShotButton.setOnClickListener {
            val intent = Intent(this, AddPullActivity::class.java)
            startActivity(intent)
        }
    }

}