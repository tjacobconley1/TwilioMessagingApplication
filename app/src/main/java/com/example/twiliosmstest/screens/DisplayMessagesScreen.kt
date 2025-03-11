package com.example.twiliosmstest.screens

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.twiliosmstest.R
import com.example.twiliosmstest.Utils
import com.example.twiliosmstest.database.messages.Message
import com.example.twiliosmstest.viewmodels.DisplayMessagesScreenViewModel

@Composable
fun DisplayMessagesScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: DisplayMessagesScreenViewModel
) {
    val messages by viewModel.messages.collectAsState(emptyList())
    var sortedMessages = messages.sortedByDescending { it.timestamp }
    var showImageAttachment by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(8.dp))
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
                    MessageItem(
                        message = message,
                        onClickImage = {
                            showImageAttachment = !showImageAttachment
                        },
                        onClickDeleteImage = {
                            viewModel.deleteMessageById(message.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MessageImageWidget(
    modifier: Modifier = Modifier,
    image: Painter? = null
) {
    image?.let {
        Image(
            painter = it,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun MessageItem(
    message: Message,
    onClickDeleteImage: () -> Unit,
    onClickImage: () -> Unit
) {
    var showImageAttachment by remember { mutableStateOf(false) }

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
            Spacer(modifier = Modifier.height(8.dp))

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                onClickImage()
                showImageAttachment = !showImageAttachment
            }) {
                Text("Image attachment")
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                onClickDeleteImage()
            }) {
                Text("Delete Message")
            }

            Spacer(modifier = Modifier.height(8.dp))
            if (showImageAttachment) {
                MessageImageWidget(
                    image = painterResource(id = R.drawable.ic_launcher_foreground)
                )
            }
        }
    }
}
