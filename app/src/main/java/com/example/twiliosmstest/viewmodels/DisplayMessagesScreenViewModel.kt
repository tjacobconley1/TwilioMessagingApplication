package com.example.twiliosmstest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twiliosmstest.database.messages.Message
import com.example.twiliosmstest.database.messages.MessagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayMessagesScreenViewModel @Inject constructor(private val repository: MessagesRepository) : ViewModel() {

    private val _messages = repository.getAllMessages()
    val messages: Flow<List<Message>> = _messages

    suspend fun getMessageById(id: Int): Message? {
        return repository.getMessageById(id)
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch {
            repository.deleteMessage(message)
        }
    }

    fun deleteMessageById(id: Int) {
        viewModelScope.launch {
            repository.deleteMessageById(id)
        }
    }

    fun insertMessage(message: Message) {
        viewModelScope.launch {
            repository.insertMessage(message)
        }
    }
}
