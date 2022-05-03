package com.example.gameappdev.database

import androidx.room.Database
import androidx.room.RoomDatabase

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