package com.example.twiliosmstest.database.messages

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.twiliosmstest.R

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sender: String = "",
    val recipient: String = "",
    val content: String = "",
    val timestamp: Long = 0L,
    val status: String = "",
    val imageURI: String = R.drawable.ic_launcher_foreground.toString()
)
