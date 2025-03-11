package com.example.twiliosmstest.viewmodels

import androidx.lifecycle.ViewModel
import com.example.twiliosmstest.database.messages.Message
import com.example.twiliosmstest.database.messages.MessagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: MessagesRepository
) : ViewModel() {

    suspend fun getMessageById(id: Int): Message? {
        return repository.getMessageById(id)
    }
}
