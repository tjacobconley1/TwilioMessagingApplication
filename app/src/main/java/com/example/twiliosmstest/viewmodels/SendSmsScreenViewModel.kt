package com.example.twiliosmstest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twiliosmstest.database.Message
import com.example.twiliosmstest.database.MessagesRepository
import com.example.twiliosmstest.network.RetrofitClient
import com.example.twiliosmstest.network.SmsRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendSmsScreenViewModel @Inject constructor(private val repository: MessagesRepository): ViewModel() {

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
                    insertNewSentMessage(
                        Message(
                            sender = "CurrentUserName",
                            recipient = phoneNumber,
                            content = message,
                            timestamp = System.currentTimeMillis(),
                            status = "Success"
                        )
                    )
                } else {
                    onResult("Failed to send SMS")
                    insertNewSentMessage(
                        Message(
                            sender = "CurrentUserName",
                            recipient = phoneNumber,
                            content = message,
                            timestamp = System.currentTimeMillis(),
                            status = "Failed"
                        )
                    )
                }
            } catch (e: Exception) {
                onResult("Error: ${e.message}")
            }
        }
    }

    private fun insertNewSentMessage(message: Message) {
        viewModelScope.launch {
            repository.insertMessage(message)
        }
    }

//    private fun getAllMessages() {
//        viewModelScope.launch {
//            repository.getAllMessages().collect { list ->
//                (messages as MutableStateFlow).value = list
//            }
//        }
//    }
}