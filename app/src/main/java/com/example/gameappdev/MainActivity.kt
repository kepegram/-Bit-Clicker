package com.example.gameappdev

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.datastore.preferences.preferencesDataStore
import com.example.gameappdev.ui.theme.AppTheme
import com.example.gameappdev.ui.theme.GameAppDevTheme

val Context.dataStore by preferencesDataStore("settings")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.window.statusBarColor = ContextCompat.getColor(this,R.color.black)
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Ensures new game is only initialized once.
                    val callCounter: MutableState<Int> = remember { mutableStateOf(0) }

                    //Ensures persistence of display counter when going from home screen to new screen, etc.
                    val displayCounter: MutableState<Int> = remember { mutableStateOf(0) }
                    AppMainScreen(context = applicationContext, callCounter, displayCounter)
                }
            }
        }
    }
}