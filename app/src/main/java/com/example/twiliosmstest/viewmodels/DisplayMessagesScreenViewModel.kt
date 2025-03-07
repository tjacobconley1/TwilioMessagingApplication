package com.example.twiliosmstest.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.twiliosmstest.database.Message
//import com.example.twiliosmstest.database.MessagesDatabase
//import com.example.twiliosmstest.database.MessagesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//class DisplayMessagesScreenViewModel(applicationContext: Context) : AndroidViewModel(applicationContext.applicationContext as Application) {
class DisplayMessagesScreenViewModel: ViewModel() {
    // private val repository: MessagesRepository

    // val messages: StateFlow<List<Message>> = MutableStateFlow(emptyList())


//    init {
//        val dao = MessagesDatabase.getMessagesDatabase(applicationContext).messageDao()
//        // repository = MessagesRepository(dao)
//    }


//    private fun getAllMessages() {
//        viewModelScope.launch {
//            repository.getAllMessages().collect { list ->
//                (messages as MutableStateFlow).value = list
//            }
//        }
//    }

}