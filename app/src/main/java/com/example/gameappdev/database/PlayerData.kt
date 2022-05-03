package com.example.gameappdev.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.*
import java.io.IOException


@Entity(tableName = "PlayerDataTable")
data class PlayerData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "level") var level: Int = 1,
    @ColumnInfo(name = "baseClickValue") var baseClickValue: Int = 1,
    @ColumnInfo(name = "perClickMultiplier") var perClickMultiplier: Int = 1,
    @ColumnInfo(name = "expCurrency") var expCurrency: Int = 0,
)

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


@Dao
interface PlayerDataDao {
    //@Query("SELECT id,level, baseClickValue, perClickMultiplier, expCurrency FROM PlayerData")
    //fun getPlayerData(): LiveData<List<PlayerData>>

    @Insert
    suspend fun addPlayerData(playerData: PlayerData)

    @Delete
    suspend fun deletePlayerData(playerData: PlayerData)
}

@Database(entities = [PlayerData::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): PlayerDataDao
}

fun fetchPlayerStartData () {
    println("Fetching jSon")
    val client = OkHttpClient()
    val url = "https://github.com/rziegl2/jsonexp/db.json"

    val request = Request.Builder()
        .url(url)
        .build()


    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) {
            val body = response.body?.string()
            print(body)
            //val apResult = Gson().fromJson(body, GsonPlayerData::class.java)
            //val apResult = Gson().fromJson(body, Array<PlayerData>::class.java)
            //val apResult: List<dataForRoom> = Gson().fromJson(body, Array<dataForRoom>::class.java).toList()
        }
    })
}
