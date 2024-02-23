package com.alexprosd.dialedin.entrydata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface EntryDao {

    @Query("SELECT * FROM entry_table")
    fun getAll(): List<Entry>

    @Query ("SELECT * FROM entry_table WHERE id = :id")
    suspend fun findById(id: Int): Entry

    @Upsert
    suspend fun upsertEntry(entry: Entry)

    @Delete
    suspend fun deleteEntry(entry: Entry)

    @Query("DELETE FROM entry_table")
    suspend fun deleteAll()
}