package com.example.gameappdev

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

// function that creates the bottom nav bar

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Store,
        NavigationItem.Settings
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.image,
                    contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

// function that creates the navhost and defines the routes
/*
@Composable
fun Navigation(navController: NavHostController, openDrawer: () -> Unit) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController = navController, openDrawer = { openDrawer() })
        }
        composable(NavigationItem.Store.route) {
            StoreScreen(navController = navController)
        }
        composable(NavigationItem.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(NavigationItem.NewGame.route) {
            NewGameScreen(navController = navController)
        }
    }
}

 */