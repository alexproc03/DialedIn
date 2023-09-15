package com.example.dialedin.entrydata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entry (
    val title: String,
    val bean: String,
    val grindSize: Double,
    val dose: Double,
    val yield: Double,
    val preInfuse: Double,
    val time: Double,
    val notes: String,
    val rating: Int,
    val favorite: Boolean,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)