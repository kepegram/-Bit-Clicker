package com.example.gameappdev.database

import android.app.Application
import android.content.Context

class DataApplication(applicationContext: Context) {
    //Created when needed. Not on app start necessarily.
    //Makes the database persist.
    val database: PlayerDatabase by lazy { PlayerDatabase.getDatabase(applicationContext) }
}