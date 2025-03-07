package com.example.twiliosmstest.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.twiliosmstest.database.Message
//import com.example.twiliosmstest.database.MessagesDatabase
//import com.example.twiliosmstest.database.MessagesRepository
import com.example.twiliosmstest.network.RetrofitClient
import com.example.twiliosmstest.network.SmsRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//class SendSmsScreenViewModel(applicationContext: Context) : AndroidViewModel(applicationContext.applicationContext as Application){
class SendSmsScreenViewModel: ViewModel(){
    // private val repository: MessagesRepository

    // To hold the response from sendSms function (e.g., success or failure)
//    private val _smsResponse = MutableStateFlow<String>("")
//    val smsResponse: StateFlow<String> get() = _smsResponse

    // For observing messages from the database
//    private val messages: StateFlow<List<Message>> = MutableStateFlow(emptyList())

//    init {
//        // Initialize the repository
//        val dao = MessagesDatabase.getMessagesDatabase(applicationContext).messageDao()
//        repository = MessagesRepository(dao)
//    }

    fun sendSms(
        phoneNumber: String,
        message: String,
        onResult: (String) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.sendSms(
                    SmsRequest(phoneNumber, message)
                )
                if (response.isSuccessful) {
                    onResult("SMS Sent Successfully")
//                    insertNewSentMessage(
//                        Message(
//                            sender = "CurrentUserName",
//                            recipient = phoneNumber,
//                            content = message,
//                            timestamp = System.currentTimeMillis(),
//                            status = "Success"
//                        )
//                    )
                } else {
                    onResult("Failed to send SMS")
//                    insertNewSentMessage(
//                        Message(
//                            sender = "CurrentUserName",
//                            recipient = phoneNumber,
//                            content = message,
//                            timestamp = System.currentTimeMillis(),
//                            status = "Failed"
//                        )
//                    )
                }
            } catch (e: Exception) {
                onResult("Error: ${e.message}")
            }
        }
    }

//    private fun getAllMessages() {
//        viewModelScope.launch {
//            repository.getAllMessages().collect { list ->
//                (messages as MutableStateFlow).value = list
//            }
//        }
//    }

//    private fun insertNewSentMessage(message: Message) {
//        viewModelScope.launch {
//            repository.insertMessage(message)
//        }
//    }
}