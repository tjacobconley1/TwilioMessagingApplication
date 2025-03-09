package com.example.twiliosmstest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twiliosmstest.database.Message
import com.example.twiliosmstest.database.MessagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayMessagesScreenViewModel @Inject constructor(private val repository: MessagesRepository): ViewModel() {

    private val _messages = repository.getAllMessages()
    val messages: Flow<List<Message>> = _messages

    fun insertMessage(message: Message) {
        viewModelScope.launch {
            repository.insertMessage(message)
        }

    }
}