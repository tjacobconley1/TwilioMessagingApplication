package com.example.twiliosmstest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twiliosmstest.database.messages.Message
import com.example.twiliosmstest.database.messages.MessagesRepository
import com.example.twiliosmstest.network.LogRequest
import com.example.twiliosmstest.network.RetrofitClient
import com.example.twiliosmstest.network.SmsRequest
import com.example.twiliosmstest.screens.SendSmsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendSmsScreenViewModel @Inject constructor(private val repository: MessagesRepository) : ViewModel() {

    private val _screenState = MutableStateFlow<SendSmsScreenState>(SendSmsScreenState.Started("Send SMS", ""))
    val screenState: StateFlow<SendSmsScreenState> = _screenState

    var lastMessageStatus: MutableStateFlow<String> = MutableStateFlow("")
    var phoneNumber: MutableStateFlow<String> = MutableStateFlow("")

    fun sendSms(
        phoneNumber: String,
        message: String,
        onResult: (String) -> Unit
    ) {
        _screenState.value = SendSmsScreenState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.sendSms(
                    SmsRequest(phoneNumber, message)
                )
                RetrofitClient.apiService.logToServer(
                    LogRequest(
                        "SMS SENDING",
                        "To: [$phoneNumber], Message: $message"
                    )
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
            _screenState.value = SendSmsScreenState.Started("Send SMS", lastMessageStatus.value)
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
