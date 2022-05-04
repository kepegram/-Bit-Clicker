package com.example.gameappdev

import android.os.Bundle
import android.util.Log.d
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.gameappdev.api.call.fetchPlayerStartData
import com.example.gameappdev.database.PlayerData
import com.example.gameappdev.database.PlayerDatabase

import com.example.gameappdev.ui.theme.GameAppDevTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //fetchPlayerStartData(applicationContext)
        super.onCreate(savedInstanceState)
        setContent {
            GameAppDevTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppMainScreen(applicationContext)
                }
            }
        }

    }


}

