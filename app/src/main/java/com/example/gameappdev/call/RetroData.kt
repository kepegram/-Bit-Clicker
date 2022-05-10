package com.example.gameappdev.call

import com.example.gameappdev.database.PlayerData
import retrofit2.http.GET

interface RetroData {
    @GET("/json.io")
    suspend fun getData(): PlayerData
}