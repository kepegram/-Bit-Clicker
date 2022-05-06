package com.example.gameappdev.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlayerData::class], version = 1)
abstract class PlayerDatabase : RoomDatabase() {
    abstract fun playerDataDao(): PlayerDataDao
}