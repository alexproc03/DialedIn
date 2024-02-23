package com.alexprosd.dialedin.entrydata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entry_table")
data class Entry(
    @PrimaryKey (autoGenerate = true) val id: Int? = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "bean") val bean: String,
    @ColumnInfo(name = "grind_size") val grindSize: Double,
    @ColumnInfo(name = "dose") val dose: Double,
    @ColumnInfo(name = "yield") val yield: Double,
    @ColumnInfo(name = "pre_infuse") val preInfuse: Double,
    @ColumnInfo(name = "time") val time: Double,
    @ColumnInfo(name = "notes") val notes: String,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "favorite") val favorite: Boolean,
)