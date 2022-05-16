package com.example.gameappdev.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gameappdev.navigation.TopBar
import com.example.gameappdev.viewmodel.PlayerViewModel

// Composable formatting the store screen

@Composable
fun StoreScreen(navController: NavController, vm: PlayerViewModel) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Store",
                buttonIcon = Icons.Filled.ArrowBack,
                onButtonClicked = { navController.navigate("newGame") },
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Upgrades(vm,navController)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
        }
    }
}

// Composable creating each store screen card

@Composable
fun Upgrades(vm: PlayerViewModel, navController: NavController) {

    LazyColumn()
    {
        items(2) {index ->
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = 10.dp,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 25.dp, bottom = 25.dp)
                    .fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (index == 0) {
                        Text(
                            text = "Upgrade BaseClick Value",
                            fontSize = 30.sp,
                            modifier = Modifier.padding(top = 25.dp, start = 5.dp)
                        )
                        Text(
                            text = "Price ${vm.getBaseClickUpgradePrice().toString(radix = 2)}",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 45.dp, start = 5.dp)
                        )
                        Text(
                            text = "Price ${vm.getBaseClickUpgradePrice()}",
                            fontSize = 10.sp,
                            modifier = Modifier.padding(top = 5.dp, start = 5.dp)
                        )
                    }
                    else{
                        Text(
                            text = "Upgrade Multiplier",
                            fontSize = 30.sp,
                            modifier = Modifier.padding(top = 25.dp, start = 5.dp)
                        )
                        Text(
                            text = "Price ${vm.getMultiUpgradePrice().toString(radix = 2)}",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 45.dp, start = 5.dp)
                        )
                        Text(
                            text = "Price ${vm.getMultiUpgradePrice()}",
                            fontSize = 10.sp,
                            modifier = Modifier.padding(top = 5.dp, start = 5.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.padding(bottom = 50.dp))

                val openDialog = remember { mutableStateOf(false) }
                Button(
                    onClick = { openDialog.value = true },
                    modifier = Modifier
                        .scale(1f)
                        .padding(top = 65.dp, bottom = 10.dp, end = 5.dp),
                    enabled = (index == 0 && vm.checkBaseAmount()) || (index == 1 && vm.checkMultiAmount())

                ) {
                    Text("PURCHASE!")
                }
                if (openDialog.value) {
                    AlertDialog(
                        onDismissRequest = { openDialog.value = false },
                        confirmButton = {
                            TextButton(
                                onClick = { openDialog.value = false
                                    //Deals with upgrade onConfirm click.
                                    if (index == 0) {
                                        vm.dealWithBaseClickUpgrade()
                                        run { navController.navigate("newGame") }
                                    }
                                    else {
                                        vm.dealWithMultiUpgrade()
                                        run { navController.navigate("newGame") }
                                    }

                                }) {
                                Text("Confirm")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = { openDialog.value = false }) {
                                Text("Cancel")
                            }
                        },
                        title = { Text(text = "Please Confirm") },
                        text = { Text("Are you sure you would like to proceed with this upgrade?") }
                    )
                }
            }
        }
    }
}