package com.example.gameappdev

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.*
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch

@Composable
fun AppMainScreen() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {
            NavHost(
                navController = navController,
                //startDestination = NavigationItem.Home.route
                startDestination = "splash_screen"
            ) {
                composable("splash_screen") {
                    SplashScreen(navController = navController)
                }
                composable(NavigationItem.Home.route) {
                    HomeScreen(navController = navController, openDrawer = { openDrawer() })
                }
                composable(NavigationItem.Settings.route) {
                    SettingsScreen(navController = navController)
                }
                composable(NavigationItem.NewGame.route) {
                    NewGameScreen(navController = navController)
                }
                composable(DrawerScreens.Credits.route) {
                    CreditsScreen(navController = navController)
                }
            }
        }
    }
}