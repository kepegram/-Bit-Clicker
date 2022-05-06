package com.example.gameappdev.call

import android.content.Context
import android.util.Log.d
import androidx.room.Room
import com.example.gameappdev.database.PlayerData
import com.example.gameappdev.database.PlayerDatabase
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Fetch using OkHttpClient() and the github Json.
fun fetchPlayerStartData(applicationContext: Context) {
    val url = "https://rziegl2.github.io"
    val api = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetroData::class.java)

    //Makes a new call to the rest api.
    GlobalScope.launch(Dispatchers.IO) {
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

fun fetchDatabase(applicationContext: Context): PlayerDatabase{
    val db = Room.databaseBuilder(
        applicationContext,
        PlayerDatabase::class.java, "PlayerDataTable"
    ).build()

    return db
}
