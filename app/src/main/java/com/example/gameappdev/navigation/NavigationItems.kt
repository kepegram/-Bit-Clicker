package com.example.gameappdev.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

// sealed class created for defining the navigation routes between screens

sealed class NavigationItem(var route: String, val image: ImageVector, var title: String) {
    object Home : NavigationItem("home", image = Icons.Filled.Home,  "Home")
    object Settings : NavigationItem("settings", image = Icons.Filled.Settings, "Settings")
    object NewGame : NavigationItem("newGame", image = Icons.Filled.Add, "New Game")
}