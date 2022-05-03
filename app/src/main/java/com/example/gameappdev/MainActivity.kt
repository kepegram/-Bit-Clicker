package com.example.gameappdev

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log.d
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import com.example.gameappdev.database.PlayerData
import com.example.gameappdev.database.PlayerDatabase

import com.example.gameappdev.database.fetchPlayerStartData
import com.example.gameappdev.ui.theme.GameAppDevTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //fetchPlayerStartData()
        //val instance = PlayerDatabase.getInstance(context = applicationContext)
        //val playerDataDao = instance.playerDataDao()
        //val allPlayerData = playerDataDao.getPlayerData()
        //println(allPlayerData.toString())

        fun databaseCreation() = runBlocking {
            val db = Room.databaseBuilder(
                applicationContext,
                PlayerDatabase::class.java, "PlayerDataTable"
            ).build()
            launch{
                //val context = ApplicationProvider.getApplicationContext<Context>()
                db.playerDataDao().addPlayerData(PlayerData(0, 1, 1, 1, 1))
                val allPlayerData = db.playerDataDao().getPlayerData()
                d("test","findddd ${allPlayerData.toString()}")
            }
        }

        databaseCreation()

        super.onCreate(savedInstanceState)
        setContent {
            GameAppDevTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppMainScreen()
                }
            }
        }

    }


}

