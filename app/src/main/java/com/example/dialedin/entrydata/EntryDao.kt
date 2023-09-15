package com.example.dialedin.entrydata

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


interface EntryDao {

    @Upsert
    suspend fun upsertEntry(entry: Entry)

    @Delete
    suspend fun deleteEntry(entry: Entry)

    @Query("SELECT * FROM entry ORDER BY rating DESC")
    fun getEntriesOrderedByRating(): Flow<List<Entry>>

    @Query("SELECT * FROM entry ORDER BY title ASC")
    fun getEntriesOrderedByTitle(): Flow<List<Entry>>

    @Query("SELECT * FROM entry ORDER BY bean ASC")
    fun getEntriesOrderedByBeans(): Flow<List<Entry>>
}