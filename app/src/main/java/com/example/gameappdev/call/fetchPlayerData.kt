package com.example.gameappdev.call

import android.content.Context
import android.util.Log.d
import androidx.room.Room
import com.example.gameappdev.database.PlayerData
import com.example.gameappdev.database.PlayerDatabase
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Fetch using Retrofit and the github Json.
fun fetchPlayerStartData(applicationContext: Context) {
    val url = "https://rziegl2.github.io"

    //Creates usable call and converts data.
    val api = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetroData::class.java)

    //Makes a new call to the rest api.
    GlobalScope.launch(Dispatchers.IO) {

        //Gets data from the api call.
        val response = api.getData()
        d("test", "findfff $response")

        fun databaseCreation(apResult: PlayerData) = runBlocking {
            val db = Room.databaseBuilder(
                applicationContext,
                PlayerDatabase::class.java, "PlayerDataTable"
            ).build()
            launch {
                d("test", "id${apResult.id}")
                d("test", "test ${apResult.toString()}")

                //Inserting the apResult data from api call into DB.
                db.playerDataDao().addPlayerData(
                    PlayerData
                        (
                        apResult.id,
                        apResult.level,
                        apResult.baseClickValue,
                        apResult.perClickMultiplier,
                        apResult.expCurrency
                    )
                )

                //allPlayerData holds the player Data from the Database.
                val allPlayerData = db.playerDataDao().getPlayerData()
                d("test", "findxxx ${allPlayerData[0]}")

                db.close()

            }
        }
        databaseCreation(response)
    }

}

//Returns a build of the db to make it accessible.
fun fetchDatabase(applicationContext: Context): PlayerDatabase {
    return Room.databaseBuilder(
        applicationContext,
        PlayerDatabase::class.java, "PlayerDataTable"
    ).build()
}
