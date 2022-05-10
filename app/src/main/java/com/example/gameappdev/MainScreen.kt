package com.example.gameappdev

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.*
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.*
import com.example.gameappdev.database.PlayerData

@Composable
fun AppMainScreen(context: Context, gameInfo: GameInfo) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Navigation(navController,context = context, gameInfo= gameInfo)
    }
}