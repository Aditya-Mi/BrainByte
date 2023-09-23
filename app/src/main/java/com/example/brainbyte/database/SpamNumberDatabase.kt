package com.example.brainbyte.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SpamNumber::class],version =1, exportSchema = false )
abstract class SpamNumberDatabase: RoomDatabase() {
    abstract fun spamNumberDao(): SpamNumberDao

    companion object{
        @Volatile
        private var INSTANCE: SpamNumberDatabase?=null
        fun getDatabase(context: Context): SpamNumberDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpamNumberDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}