package com.example.gameappdev.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.gameappdev.navigation.TopBar
import com.example.gameappdev.call.fetchPlayerStartData
import com.example.gameappdev.database.DataApplication
import com.example.gameappdev.navigation.BottomNavigationBar
import com.example.gameappdev.ui.theme.Shapes
import com.example.gameappdev.viewmodel.PlayerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun HomeScreen(
    navController: NavController,
    openDrawer: () -> Unit,
    context: Context,
    vm: PlayerViewModel
)
{
    //Allows for intent retrieval from vm.
    val intent = vm.dealWithIntent()
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

    Scaffold(
        topBar = {
            TopBar(
                title = "Bit Clicker Home",
                buttonIcon = Icons.Filled.Menu,
                onButtonClicked = { openDrawer() },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    run { navController.navigate("newGame") }

                    val res = GlobalScope.launch(context = Dispatchers.IO) {
                        //If Database doesn't exist, fetch api and create db.
                        if (DataApplication(context).database.playerDataDao()
                                .getPlayerData().isEmpty()) {
                            fetchPlayerStartData(context,vm)
                        }
                        //If database does exist set the display counter, "fake persist".
                        else{
                            vm.displayCounter.value = vm.getCount()
                        }
                    }
                    //Wait until Db is loaded.
                    runBlocking {
                        res.join()
                        //Add values to playerData in vm.
                        vm.updateEmpty()
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
                BottomNavigationBar(navController)
            }
        }
    ) {
        Column(
            verticalArrangement= Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            val infiniteTransition = rememberInfiniteTransition()

            val heartSize by infiniteTransition.animateFloat(
                initialValue = 100.0f,
                targetValue = 250.0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(800, delayMillis = 100,easing = FastOutLinearInEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
            Image(
                painter = painterResource(id = com.example.gameappdev.R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(heartSize.dp)
            )
            Text(
                text = "BIT CLICKER",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(30.dp),
            )
        }
        ExtendedFloatingActionButton(
            text = { showText() },
            onClick = { startActivity(context,intent, Bundle.EMPTY)
            },
            shape = Shapes.small,
            modifier = Modifier.padding(start = 103.dp)
        )
    }
}

@Composable
fun showText(){
    Text("Share this Game!", modifier = Modifier.padding(10.dp))
}