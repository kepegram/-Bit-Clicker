package com.example.gameappdev.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gameappdev.navigation.BottomNavigationBar
import com.example.gameappdev.navigation.TopBar
import com.example.gameappdev.dataStore
import com.example.gameappdev.ui.theme.Caption
import com.example.gameappdev.ui.theme.MatrixRain
import com.example.gameappdev.ui.theme.ThemeViewModel
import com.example.gameappdev.ui.theme.captionColor

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel = remember {
        ThemeViewModel(context.dataStore)
    }
    val value = viewModel.state.observeAsState().value
    val systemInDarkTheme = isSystemInDarkTheme()
    val darkModeChecked by remember(value) {
        val checked = when (value) {
            null -> systemInDarkTheme
            else -> value
        }
        mutableStateOf(checked)
    }
    val useDeviceModeChecked by remember(value) {
        val checked = when (value) {
            null -> true
            else -> false
        }
        mutableStateOf(checked)
    }
    LaunchedEffect(viewModel) {
        viewModel.request()
    }
    Scaffold(
        topBar = {
            TopBar(
                title = "Switch Theme",
                buttonIcon = Icons.Filled.ArrowBack,
                onButtonClicked = { navController.navigate("home") }
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        MatrixRain()
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(40.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Row {
                    Caption(
                        text = "\uD83C\uDF19  Dark mode",
                        color = captionColor(),
                        modifier = Modifier.weight(5f)
                    )
                    Switch(
                        checked = darkModeChecked,
                        onCheckedChange = { viewModel.switchToUseDarkMode(it) })
                }
                Row {
                    Caption(
                        text = "\uD83D\uDCF1  Use device settings",
                        color = captionColor(),
                        modifier = Modifier.weight(5f)
                    )
                    Switch(
                        checked = useDeviceModeChecked,
                        onCheckedChange = { viewModel.switchToUseSystemSettings(it) })
                }
            }
        }
    }
}