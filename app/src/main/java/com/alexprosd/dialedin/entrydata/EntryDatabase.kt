package com.alexprosd.dialedin.entrydata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entry :: class], version = 1)
abstract class EntryDatabase :RoomDatabase() {
    abstract fun EntryDao(): EntryDao

    companion object {

        @Volatile
        private var INSTANCE: EntryDatabase? = null

        fun getDatabase(context: Context): EntryDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntryDatabase::class.java,
                    "entry_database"
                ).build()
                INSTANCE = instance
                return instance

            }
        }
    }
}
