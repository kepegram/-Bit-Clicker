package com.example.gameappdev

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
/*

// drawer.kt

sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Home", "home")
    object Settings : DrawerScreens("Settings", "settings")
    object Store : DrawerScreens("Store", "store")
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Settings,
    DrawerScreens.Store
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.title,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.clickable {
                    onDestinationClicked(screen.route)
                }
            )
        }
    }
}

// drawer screens

@Composable
fun Home(/*openDrawer: () -> Unit*/) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar(
            title = "Home",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { /*openDrawer()*/ },
            navController = navController
        ) },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Navigation(navController)
    }
    /*
    Column(
        modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Home",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() },
            navController = navController
        )
    }
    */
    Column(
        verticalArrangement= Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "INFINITE CLICKER",
            fontSize = 28.sp,
            modifier = Modifier.padding(70.dp),
        )
        //Button should start a new game.
        Row(modifier = Modifier.padding(25.dp)) {
            Button(onClick = {navController.navigate("newgame")}){
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

@Composable
fun Settings(
    openDrawer: () -> Unit,
    navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Settings",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() },
            navController = navController
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "Settings",
                style = MaterialTheme.typography.h4
            )
            var title by remember { mutableStateOf("") }
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
            )
            var content by remember { mutableStateOf("") }
            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Contents") },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
            )
        }
    }
}

@Composable
fun Store(
    openDrawer: () -> Unit,
    navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Store",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() },
            navController = navController
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "Store",
                style = MaterialTheme.typography.h4
            )
            var title by remember { mutableStateOf("") }
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
            )
            var content by remember { mutableStateOf("") }
            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Contents") },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
            )
        }
    }
}

// part of appmainscreen (controlled drawer)

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
                        navController.navigate(route){
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {

// nav host for the drawer

        NavHost(
                navController = navController,
                startDestination = DrawerScreens.Home.route
            ) {
                composable(DrawerScreens.Home.route) {
                    Home(
                        navController = navController,
                        openDrawer = { openDrawer() }
                    )
                }
                composable(DrawerScreens.Settings.route) {
                    Settings(
                        navController = navController,
                        openDrawer = { openDrawer() }
                    )
                }
                composable(DrawerScreens.Store.route) {
                    Store(
                        navController = navController,
                        openDrawer = { openDrawer() }
                    )
                }
            }

// old main menu

@Composable
fun MainMenu(navController: NavHostController) {
    Column(
        verticalArrangement= Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "INFINITE CLICKER",
            fontSize = 28.sp,
            modifier = Modifier.padding(70.dp),
        )

        //Button should start a new game.
        Row(modifier = Modifier.padding(25.dp)) {
            Button(onClick = {navController.navigate("newGameView")}){
                Text(text = "New Game")
            }
        }

        //Button should resume game.
        Row(modifier = Modifier.padding(25.dp)) {
            Button(onClick = { ""}){
                Text(text = "Continue Game")
            }
        }
    }
}

@Composable
fun FunctionalNavigator(){
    val navController = rememberNavController()
    NavHost(navController = navController ,
            startDestination = "mainMenu"
    ){
        composable("mainMenu"){ MainMenu(navController)}
        composable("newGameView"){ NewGameView() }
    }
}

*/
