package com.example.itunes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ResultsOff::class),version = 1 ,exportSchema = false)
public abstract class MusicDatabase :RoomDatabase(){
    abstract fun musicDao(): MusicDao

    companion object {
        private var INSTANCE: MusicDatabase? = null

        fun getInstance(context: Context): MusicDatabase? {
            if (INSTANCE == null) {
                synchronized(MusicDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MusicDatabase::class.java, "music_database.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}