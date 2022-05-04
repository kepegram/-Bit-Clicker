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
) {

}