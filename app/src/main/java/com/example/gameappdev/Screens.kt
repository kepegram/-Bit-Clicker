package com.example.gameappdev

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gameappdev.api.call.fetchPlayerStartData
import com.example.gameappdev.database.PlayerData

//import com.example.gameappdev.database.dbInstance

// contains all the screens

@Composable
fun HomeScreen(navController: NavController, context: Context) {
    TopBar(
        title = "Infinite Clicker"
    )
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
            Button(onClick = {
                navController.navigate("newGame")
                val playerData = fetchPlayerStartData(context)
            }){
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

// Save State for player data (not working)
/*fun CreatePlayerData(){

    val string = "hello world!"
    val fos = FileOutputStream(File("/Users/ryleyziegler/cosc435/proj1/appdev/app/src/main/java/com/example/gameappdev/", "Demo.txt"))
    val bytesArray: ByteArray = string.toByteArray()
    fos.write(bytesArray)
    fos.flush()
    fos.close()
}*/

@Composable
fun NewGameScreen(navController: NavController, gameInfo: GameInfo) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        val counter: MutableState<Int> = remember { gameInfo.counter }
        val text: MutableState<String> = remember { mutableStateOf(counter.value.toString())}
        if (gameInfo.radix.value != 10){
            if (gameInfo.radix.value == 2){
                text.value = counter.value.toString(radix= 2) + "[2]"
            }
            else if (gameInfo.radix.value == 16){
                text.value = "0x" + counter.value.toString(radix= 16)
            }
        }

        Text(
            text = text.value,
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
fun StoreScreen(navController: NavController, gameInfo: GameInfo) {
    TopBar(
        title = "Store"
    )
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

@Composable
fun SettingsScreen(navController: NavController, gameInfo: GameInfo) {
    TopBar(
        title = "Settings"
    )
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
        Row(modifier = Modifier.padding(25.dp)) {
            Button(onClick = { gameInfo.setRad(2) }) {
                Text(text = "Binary")
            }
        }
        Row(modifier = Modifier.padding(25.dp)) {
            Button(onClick = { gameInfo.setRad(10) }) {
                Text(text = "Decimal")
            }
        }
        Row(modifier = Modifier.padding(25.dp)) {
            Button(onClick = { gameInfo.setRad(16) }) {
                Text(text = "Hexidecimal")
            }
        }
    }
}
