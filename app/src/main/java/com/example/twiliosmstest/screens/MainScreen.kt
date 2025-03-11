package com.example.twiliosmstest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.twiliosmstest.R
import com.example.twiliosmstest.viewmodels.MainScreenViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainScreenViewModel
) {
    val image = painterResource(R.drawable.logo)

    Column(
        modifier = modifier.fillMaxSize().padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        image.let {
            Image(
                image,
                contentDescription = "logo",
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate("send_sms_screen")
            }
        ) {
            Text("Send SMS Message Screen")
        }
    }
}
