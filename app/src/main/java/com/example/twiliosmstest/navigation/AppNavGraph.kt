package com.example.twiliosmstest.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.twiliosmstest.screens.DisplayMessagesScreen
import com.example.twiliosmstest.screens.MainScreen
import com.example.twiliosmstest.screens.SendSmsScreen
import com.example.twiliosmstest.screens.uicomponents.AppTopBar
import com.example.twiliosmstest.viewmodels.DisplayMessagesScreenViewModel
import com.example.twiliosmstest.viewmodels.MainScreenViewModel
import com.example.twiliosmstest.viewmodels.SendSmsScreenViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    val mainScreenViewModel: MainScreenViewModel = viewModel()
    val sendSmsScreenViewModel: SendSmsScreenViewModel = viewModel()
    val displayMessagesScreenViewModel: DisplayMessagesScreenViewModel = viewModel()

    NavHost(navController = navController, startDestination = "main_screen") {
        // SEND SMS SCREEN ROUTE
        composable("send_sms_screen") {
            Spacer(modifier = Modifier.height(20.dp))
            Scaffold(
                modifier = Modifier.fillMaxSize().statusBarsPadding(),
                topBar = {
                    AppTopBar(
                        title = "Send SMS",
                        onClickBack = {
                            navController.popBackStack()
                        },
                        onClickViewMessages = {
                            navController.navigate("display_messages_screen")
                        },
                        onClickLogout = {
                            navController.navigate("main_screen")
                        }
                    )
                }
            ) { innerPadding ->
                SendSmsScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    sendSmsScreenViewModel

                )
            }
        }

        // MAIN/LOGIN SCREEN ROUTE
        composable("main_screen") {
            Spacer(modifier = Modifier.height(20.dp))
            Scaffold(modifier = Modifier.fillMaxSize().statusBarsPadding()) { innerPadding ->
                MainScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    mainScreenViewModel
                )
            }
        }

        // DISPLAY MESSAGES SCREEN ROUTE
        composable("display_messages_screen") {
            Spacer(modifier = Modifier.height(20.dp))
            Scaffold(
                modifier = Modifier.fillMaxSize().statusBarsPadding(),
                topBar = {
                    AppTopBar(
                        title = "Messages",
                        onClickBack = {
                            navController.popBackStack()
                        },
                        onClickViewMessages = {
                            navController.navigate("display_messages_screen")
                        },
                        onClickLogout = {
                            navController.navigate("main_screen")
                        }
                    )
                }
            ) { innerPadding ->
                DisplayMessagesScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    displayMessagesScreenViewModel
                )
            }
        }
    }
}
