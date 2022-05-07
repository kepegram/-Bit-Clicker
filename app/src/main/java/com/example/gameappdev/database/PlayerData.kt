package com.example.gameappdev.database

import androidx.room.*
import okhttp3.*


@Entity(tableName = "PlayerDataTable")
data class PlayerData(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    @ColumnInfo(name = "level") var level: Int,
    @ColumnInfo(name = "baseClickValue") var baseClickValue: Int,
    @ColumnInfo(name = "perClickMultiplier") var perClickMultiplier: Int,
    @ColumnInfo(name = "expCurrency") var expCurrency: Int,
) {

}