package com.example.gameappdev.api.call

import android.content.Context
import android.util.Log.d
import androidx.room.Room
import com.example.gameappdev.database.PlayerData
import com.example.gameappdev.database.PlayerDatabase
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import java.io.IOException

//Fetch using OkHttpClient() and my github Json.
fun fetchPlayerStartData(applicationContext: Context) {
    val client = OkHttpClient()
    val url = "https://rziegl2.github.io/json.io/"
    val request = Request.Builder()
        .url(url)
        .build()

    //Makes a new call to the rest api.
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) {

            //Response is received from api call
            val body = response.body?.string()

            //Using Gson to convert body into list result.
            val apResult = Gson().fromJson(body, PlayerData::class.java)

            //Creates a Database runBlocking coroutine ensures completion.
            fun databaseCreation(apResult: PlayerData) = runBlocking {
                val db = Room.databaseBuilder(
                    applicationContext,
                    PlayerDatabase::class.java, "PlayerDataTable"
                ).build()
                launch{
                    d("test","id${apResult.id}")
                    d("test","test ${apResult.toString()}")
                    //val context = ApplicationProvider.getApplicationContext<Context>()

                    //Inserting the apResult data from api call into DB.
                    db.playerDataDao().addPlayerData(PlayerData
                        (apResult.id,
                        apResult.level,
                        apResult.baseClickValue,
                        apResult.perClickMultiplier,
                        apResult.expCurrency))

                    //allPlayerData holds the player Data from the Database.
                    val allPlayerData = db.playerDataDao().getPlayerData()
                    d("test","findxxx ${allPlayerData[0]}")

                }
            }

            databaseCreation(apResult)
            //val apResult: List<dataForRoom> = Gson().fromJson(body, Array<dataForRoom>::class.java).toList()
        }
    })

}