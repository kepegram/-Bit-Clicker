package com.example.gameappdev.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gameappdev.database.PlayerData
import com.example.gameappdev.ui.theme.MatrixRain
import com.example.gameappdev.viewmodel.PlayerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.gameappdev.R

@Composable
fun NewGameScreen(
    navController: NavController,
    vm: PlayerViewModel
) {
    MatrixRain()
    val scope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(1.dp)
            ) {
                //Displays the level of the player.
                Text("Level: ${vm.getCurrentLevel().toString(radix = 2)}", fontSize = 28.sp )
            }
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Circle Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(350.dp)
                    .clip(CircleShape)
                    .border(5.dp, Color.Gray, CircleShape)
            )
            TextButton(onClick = { }) {
                Text(
                    text = "CLICK BELOW!",
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Button(
                onClick = {
                    //Coroutine in order to access database.
                    //This updates the value of the players expCurrency per click.
                    GlobalScope.launch(Dispatchers.IO) {
                        //var db = DataApplication(applicationContext = context).database
                        vm.incrementCount()
                        //allPlayer[0].expCurrency ++
                        //Log.d("test", "findxxx ${count}")
                        vm.addPlayerData(
                            PlayerData(
                            0,
                            vm.db.getPlayerData()[0].level,
                            vm.db.getPlayerData()[0].baseClickValue,
                            vm.db.getPlayerData()[0].perClickMultiplier,
                            vm.playerData.value[0].expCurrency)
                        )

                        //Updating displayCounter to display the correct value.
                        vm.displayCounter.value = vm.playerData.value[0].expCurrency
                        vm.dealWithLevel()
                    }
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(300.dp)
            ) {
                Text(
                    text = vm.displayCounter.value.toString(radix = 2),
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            }
            Text(
                text = vm.displayCounter.value.toString(),
                modifier = Modifier.padding(top = 0.dp, end = 0.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Row(modifier = Modifier.padding(25.dp)) {
                Button(onClick = { navController.navigate("Store") }) {
                    Text(text = "Purchase Upgrades", color = Color.Black)
                }
            }
            Row(modifier = Modifier.padding(3.dp)) {
                Button(onClick = { navController.navigate("home") }) {
                    Text(text = "Return Home", color = Color.Black)
                }
            }
        }
    }
}