package com.example.twiliosmstest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.twiliosmstest.R
import com.example.twiliosmstest.screens.uicomponents.LoadingSpinner
import com.example.twiliosmstest.viewmodels.SendSmsScreenViewModel

@Composable
fun SendSmsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SendSmsScreenViewModel
) {
    val screenState by viewModel.screenState.collectAsState()
    var phoneNumber by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("") }
    var showStatusMessage by remember { mutableStateOf(false) }

    when (screenState) {
        is SendSmsScreenState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = (viewModel.screenState as SendSmsScreenState.Error).message)
            }
        }
        SendSmsScreenState.Loading -> {
            LoadingSpinner()
        }

        is SendSmsScreenState.Started -> {
            Box(
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        modifier =
                        Modifier
                            .clickable {
                                showStatusMessage = false
                            }
                            .onFocusEvent {},
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Phone Number") },
                        shape = RoundedCornerShape(16.dp)

                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    OutlinedTextField(
                        modifier =
                        Modifier
                            .background(Color.Transparent)
                            .fillMaxWidth()
                            .height(325.dp)
                            .onFocusEvent {},
                        value = message,
                        onValueChange = { message = it },
                        label = { Text("Message") },
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    if (showStatusMessage) {
                        if (statusMessage == "SMS Sent Successfully") {
                            Text(text = statusMessage, color = Color.Green, fontFamily = FontFamily.Monospace, fontStyle = FontStyle.Normal)
                        } else {
                            Text(text = statusMessage, fontFamily = FontFamily.Monospace, fontStyle = FontStyle.Normal, color = Color.Red)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth().height(50.dp)) {
                        Column(modifier = Modifier.weight(1f)) {
                            Button(
                                modifier = Modifier.fillMaxSize(),
                                colors = ButtonDefaults.buttonColors(),
                                onClick = {
                                    viewModel.sendSms(phoneNumber, message) { result ->
                                        statusMessage = result
                                    }
                                    showStatusMessage = true
                                    phoneNumber = ""
                                    message = ""
                                    viewModel.lastMessageStatus.value = statusMessage
                                    // (screenState as SendSmsScreenState.Started).lastMessageStatus = statusMessage
                                }
                            ) {
                                Text("Send SMS")
                            }
                        }
                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                            Image(
                                modifier = Modifier
                                    .size(50.dp)
                                    .fillMaxSize()
                                    .clickable {
                                        // TODO attach image functionality
                                    },
                                painter = painterResource(R.drawable.baseline_attachment_24),
                                contentDescription = ""
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

sealed class SendSmsScreenState {
    object Loading : SendSmsScreenState()
    data class Error(val message: String) : SendSmsScreenState()
    data class Started(val title: String, var lastMessageStatus: String) : SendSmsScreenState()
}
