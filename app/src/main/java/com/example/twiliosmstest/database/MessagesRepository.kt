package com.example.twiliosmstest.database

import kotlinx.coroutines.flow.Flow

class MessagesRepository(private val dao: MessagesDao) {

    // Insert a message
    suspend fun insertMessage(message: Message) {
        dao.insertMessage(message)
    }

    // Get messages for a recipient
    fun getMessagesForRecipient(recipient: String): Flow<List<Message>> {
        return dao.getMessagesForRecipient(recipient)
    }

    // Get all messages
    fun getAllMessages(): Flow<List<Message>> {
        return dao.getAllMessages()
    }

    // Delete a message
    suspend fun deleteMessage(message: Message) {
        dao.deleteMessage(message)
    }

    // Delete messages for a recipient
    suspend fun deleteMessagesForRecipient(recipient: String) {
        dao.deleteMessagesForRecipient(recipient)
    }

    // Delete messages for a sender
    suspend fun deleteMessagesForSender(sender: String) {
        dao.deleteMessagesForSender(sender)
    }

    // Delete message by id
    suspend fun deleteMessageById(id: Int) {
        dao.deleteMessageById(id)
    }

    // Get message by id
    suspend fun getMessageById(id: Int): Message? {
        return dao.getMessageById(id)
    }
}
