package com.example.gameappdev.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDataDao {
    @Query("SELECT * FROM PlayerDataTable")
    fun getPlayerData(): LiveData<List<PlayerData>>

    @Insert
    suspend fun addPlayerData(playerData: PlayerData)

    @Delete
    suspend fun deletePlayerData(playerData: PlayerData)
}