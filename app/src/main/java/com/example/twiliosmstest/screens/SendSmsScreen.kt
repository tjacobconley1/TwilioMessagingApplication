package com.example.twiliosmstest.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.twiliosmstest.viewmodels.SendSmsScreenViewModel

@Composable
fun SendSmsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SendSmsScreenViewModel
) {
    var phoneNumber by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("") }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center) // Center the Column within the Box
                .padding(16.dp) // Add padding around the content
        ) {
            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Message") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.sendSms(phoneNumber, message) { result ->
                        statusMessage = result
                    }
                }
            ) {
                Text("Send SMS")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = statusMessage)

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    navController.navigate("main_screen")
                }){
                Text("Go back to main screen")
            }
            Button(
                onClick = {
                    navController.navigate("display_messages_screen")
                }){
                Text("go to display messages screen")
            }
        }
    }
}
