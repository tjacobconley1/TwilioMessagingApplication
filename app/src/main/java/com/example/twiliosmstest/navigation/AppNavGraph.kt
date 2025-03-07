package com.example.twiliosmstest.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.twiliosmstest.screens.DisplayMessagesScreen
import com.example.twiliosmstest.screens.MainScreen
import com.example.twiliosmstest.screens.SendSmsScreen
import com.example.twiliosmstest.viewmodels.DisplayMessagesScreenViewModel
import com.example.twiliosmstest.viewmodels.MainScreenViewModel
import com.example.twiliosmstest.viewmodels.SendSmsScreenViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {

    val mainScreenViewModel: MainScreenViewModel = viewModel()
    val sendSmsScreenViewModel: SendSmsScreenViewModel = viewModel()
    val displayMessagesScreenViewModel: DisplayMessagesScreenViewModel = viewModel()

    NavHost(navController = navController, startDestination = "main_screen") {
        composable("send_sms_screen") {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                SendSmsScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    sendSmsScreenViewModel
                )
            }
        }
        composable("main_screen") {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MainScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    mainScreenViewModel
                )
            }
        }
        composable("display_messages_screen") {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                DisplayMessagesScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    displayMessagesScreenViewModel
                )
            }
        }
    }
}