package com.example.twiliosmstest.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.twiliosmstest.Utils
import com.example.twiliosmstest.database.Message
import com.example.twiliosmstest.viewmodels.DisplayMessagesScreenViewModel

@Composable
fun DisplayMessagesScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: DisplayMessagesScreenViewModel
) {

    val messages by viewModel.messages.collectAsState(emptyList())
    var sortedMessages = messages.sortedByDescending { it.timestamp }

    Column(modifier = Modifier.fillMaxWidth()){
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                navController.popBackStack()
            }) {
            Text("Go back to send sms screen")
        }
        if (messages.isEmpty()) {
            Text(
                text = "No messages available",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(sortedMessages) { message ->
                    MessageItem(message = message)
                }
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "From: ${message.sender}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "To: ${message.recipient}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = message.content)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Timestamp: ${Utils.formatTimestamp(message.timestamp)}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Status: ${message.status}")
        }
    }
}

