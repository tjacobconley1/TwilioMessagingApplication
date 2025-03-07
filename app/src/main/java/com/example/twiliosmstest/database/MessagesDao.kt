package com.example.twiliosmstest.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDao {
    @Insert
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM messages WHERE recipient = :recipient")
    fun getMessagesForRecipient(recipient: String): Flow<List<Message>>

    @Query("SELECT * FROM messages WHERE sender = :sender")
    fun getMessagesForSender(sender: String): Flow<List<Message>>

    @Query("SELECT * FROM messages")
    fun getAllMessages(): Flow<List<Message>>

    @Delete
    suspend fun deleteMessage(message: Message)

    @Update
    suspend fun updateMessage(message: Message)

    @Query("DELETE FROM messages")
    suspend fun deleteAllMessages()

    @Query("DELETE FROM messages WHERE recipient = :recipient")
    suspend fun deleteMessagesForRecipient(recipient: String)

    @Query("DELETE FROM messages WHERE sender = :sender")
    suspend fun deleteMessagesForSender(sender: String)

    @Query("DELETE FROM messages WHERE id = :id")
    suspend fun deleteMessageById(id: Int)

    @Query("SELECT * FROM messages WHERE id = :id")
    suspend fun getMessageById(id: Int): Message?
}
