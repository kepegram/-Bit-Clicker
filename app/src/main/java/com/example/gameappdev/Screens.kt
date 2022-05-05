package com.example.gameappdev

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// contains all the screens

@Composable
fun HomeScreen(navController: NavController, openDrawer: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Infinite Clicker",
                buttonIcon = Icons.Filled.Menu,
                onButtonClicked = { openDrawer() }
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Column(
            verticalArrangement= Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "INFINITE CLICKER",
                fontSize = 28.sp,
                modifier = Modifier.padding(70.dp),
            )
            //Button should start a new game.
            Row(modifier = Modifier.padding(25.dp)) {
                Button(onClick = {navController.navigate("newGame")}){
                    Text(text = "New Game")
                }
            }
            //Button should resume game.
            Row(modifier = Modifier.padding(25.dp)) {
                Button(onClick = {""}){
                    Text(text = "Continue Game")
                }
            }
        }
    }
}

@Composable
fun NewGameScreen(navController: NavController) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            val counter: MutableState<Int> = remember { mutableStateOf(0) }
            Text(
                text = counter.value.toString(),
                modifier = Modifier.padding(16.dp),
            )
            Button(
                onClick = {
                    counter.value++
                }
            ) {
                Text(
                    text = "CLICK ME!"
                )
            }
            Row(modifier = Modifier.padding(25.dp)) {
                Button(onClick = { navController.navigate("home") }) {
                    Text(text = "Return Home")
                }
            }
        }
    }

@Composable
fun StoreScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Infinite Clicker",
                buttonIcon = Icons.Filled.Menu,
                onButtonClicked = { "" }
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Store View",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        }
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Infinite Clicker",
                buttonIcon = Icons.Filled.Menu,
                onButtonClicked = { "" }
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Settings View",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        }
    }
}

@Composable
fun CreditsScreen(navController: NavController) {
    TopBar(
        title = "Credits",
        buttonIcon = Icons.Filled.Menu,
        onButtonClicked = { "" }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "App by: Ryley, Kadin, William, Everett",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Row(modifier = Modifier.padding(25.dp)) {
            Button(onClick = { navController.navigate("home") }) {
                Text(text = "Return Home")
            }
        }
    }
}
