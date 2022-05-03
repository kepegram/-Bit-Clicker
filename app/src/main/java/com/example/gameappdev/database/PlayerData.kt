package com.example.gameappdev.database

import android.app.Application
import android.content.Context
import android.util.Log.d
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.test.core.app.ApplicationProvider
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.*
import java.io.IOException


@Entity(tableName = "PlayerDataTable")
data class PlayerData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "level") var level: Int,
    @ColumnInfo(name = "baseClickValue") var baseClickValue: Int,
    @ColumnInfo(name = "perClickMultiplier") var perClickMultiplier: Int,
    @ColumnInfo(name = "expCurrency") var expCurrency: Int,
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
    @Query("SELECT * FROM PlayerDataTable")
    fun getPlayerData(): LiveData<List<PlayerData>>

    @Insert
    suspend fun addPlayerData(vararg playerData: PlayerData)

    @Delete
    suspend fun deletePlayerData(playerData: PlayerData)
}

@Database(entities = [PlayerData::class], version = 1)
abstract class PlayerDatabase : RoomDatabase() {
    abstract fun playerDataDao(): PlayerDataDao
    //abstract val playerDatabaseDao: PlayerDataDao

    /*companion object {

        @Volatile
        private var INSTANCE: PlayerDatabase? = null

        fun getInstance(context: Context): PlayerDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlayerDatabase::class.java,
                        "PlayerDataTable"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }*/

}

fun fetchPlayerStartData () {
    println("Fetching jSon")
    val client = OkHttpClient()
    val url = "https://my-json-server.typicode.com/rziegl2/playerdata"

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

 /*suspend fun dbInstance(): LiveData<List<PlayerData>>{
    val context = ApplicationProvider.getApplicationContext<Context>()
    val db = Room.databaseBuilder(
        context,
        PlayerDatabase::class.java, "PlayerDataTable"
    ).build()

     db.playerDataDao().addPlayerData(PlayerData(0, 1, 1, 1, 1))
     val allPlayerData = db.playerDataDao().getPlayerData()
     d("test","find data ${allPlayerData.value}")

     return allPlayerData

}*/
