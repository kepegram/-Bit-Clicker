package com.example.gameappdev.api.call

import android.util.Log.d
import com.example.gameappdev.database.PlayerData
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.*
import java.io.IOException

fun fetchPlayerStartData () {
    println("Fetching jSon")
    val client = OkHttpClient()
    val url = "https://rziegl2.github.io/json.io/"

    val request = Request.Builder()
        .url(url)
        .build()


    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) {
            val body = response.body?.string()
            print(body)
            val apResult = Gson().fromJson(body, PlayerData::class.java)
            d("test","test ${apResult.toString()}")
            //val apResult: List<dataForRoom> = Gson().fromJson(body, Array<dataForRoom>::class.java).toList()
        }
    })

}

data class GsonPlayerData(
    @SerializedName("id")
    var id: Int,
    @SerializedName("level")
    var level: Int,
    @SerializedName("baseClickValue")
    var baseClickValue: Int,
    @SerializedName("perClickMultiplier")
    var perClickMultiplier: Int,
    @SerializedName("expCurrency")
    var expCurrency: Int
)