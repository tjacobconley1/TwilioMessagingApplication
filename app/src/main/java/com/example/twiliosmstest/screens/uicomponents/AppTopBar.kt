package com.example.twiliosmstest.screens.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twiliosmstest.R

@Composable
fun AppTopBar(
    title: String,
    onClickBack: () -> Unit,
    onClickViewMessages: () -> Unit,
    onClickLogout: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        // Row for Back Button and Menu
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button
            Image(
                modifier = Modifier
                    .clickable { onClickBack() }
                    .size(24.dp), // Adjusted size
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                colorFilter = ColorFilter.tint(Color.Black)
            )

            // Spacer to push title to center
            Spacer(modifier = Modifier.weight(1f))

            // Title (Centered)
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(2f)
            )

            // Spacer to balance right-side menu
            Spacer(modifier = Modifier.weight(1f))

            // Dropdown Menu Button
            Box {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = true },
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = Color.Black
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("View Messages") },
                        onClick = {
                            expanded = false
                            onClickViewMessages()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Logout") },
                        onClick = {
                            expanded = false
                            onClickLogout()
                        }
                    )
                }
            }
        }
    }
}

// @Composable
// fun AppTopBar(
//    onClickBack: () -> Unit
// ){
//    Box(modifier = Modifier.fillMaxWidth().height(50.dp).background(Color.Gray)){
//        Row(modifier = Modifier
//            .fillMaxSize()
//            .padding(start = 8.dp)
//        ) {
//            Image(
//                modifier = Modifier.clickable {
//                    onClickBack()
//                }.fillMaxHeight(),
//                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
//                contentDescription = "back",
//                colorFilter = ColorFilter.tint(Color.Black),
//
//            )
//        }
//    }
// }
