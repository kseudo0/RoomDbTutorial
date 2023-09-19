package com.example.roomdbtutorial.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomdbtutorial.data.converters.RoomConverters
import com.example.roomdbtutorial.data.daos.WordDao
import com.example.roomdbtutorial.data.models.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class WordDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao


    companion object {

        private fun buildDatabase(context: Context): WordDatabase {
            return Room.databaseBuilder(context, WordDatabase::class.java, "word").build()
        }

        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getDatabaseInstance(context: Context): WordDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }
    }
}