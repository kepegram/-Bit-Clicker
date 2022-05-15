package com.example.gameappdev.call

import android.content.Context
import android.util.Log.d
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.room.Room
import com.example.gameappdev.database.DataApplication
import com.example.gameappdev.database.PlayerData
import com.example.gameappdev.database.PlayerDatabase
import com.example.gameappdev.viewmodel.PlayerViewModel
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Fetch using Retrofit and the github Json.
suspend fun fetchPlayerStartData(applicationContext: Context, vm: PlayerViewModel) {
    val url = "https://rziegl2.github.io"

    //Creates usable call and converts data.
    val api = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetroData::class.java)

    //Makes a new call to the rest api.
        //Gets data from the api call.
        val response = api.getData()
        d("test", "findfff $response")

        //New Instance of the database.
       //var db  = DataApplication(applicationContext).database

        //Adds starting values to db.
        vm.db.addPlayerData(
            PlayerData(
                response.id,
                response.level,
                response.baseClickValue,
                response.perClickMultiplier,
                response.expCurrency
        ))

}
