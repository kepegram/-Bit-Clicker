package com.example.gameappdev.database

import androidx.room.*

@Dao
interface PlayerDataDao {
    @Query("SELECT * FROM PlayerDataTable")
    fun getPlayerData(): List<PlayerData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayerData(vararg playerData: PlayerData)

    @Delete
    suspend fun deletePlayerData(playerData: PlayerData)
}