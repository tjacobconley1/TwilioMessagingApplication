package com.example.twiliosmstest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Message::class], version = 1, exportSchema = false)
abstract class MessagesDatabase: RoomDatabase() {

    abstract fun messageDao(): MessagesDao

    companion object {
        @Volatile
        private var INSTANCE: MessagesDatabase? = null

        fun getMessagesDatabase(context: Context): MessagesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MessagesDatabase::class.java,
                    "messages_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}