package com.example.gameappdev.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameappdev.database.DataApplication
import com.example.gameappdev.database.PlayerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerViewModel(context: Application): AndroidViewModel(context) {
    private var _levelMilestonesIndex:MutableState<Int> = mutableStateOf(0)
    private val _levelMilestones: MutableState<List<Int>> = mutableStateOf(listOf(50,100,200,400,800,1600,3200,6400,1))

    private val _currentLevel: MutableState<Int> = mutableStateOf(0)
    var currentLevel: MutableState<Int> = _currentLevel

    private val _displayCounter: MutableState<Int> =  mutableStateOf(0)
    var displayCounter: MutableState<Int> = _displayCounter

    private val _playerData: MutableState<List<PlayerData>> = mutableStateOf(listOf())
    val playerData: State<List<PlayerData>> = _playerData

    val db = DataApplication(context).database.playerDataDao()

    init {

        GlobalScope.launch(Dispatchers.IO) {
            _playerData.value = db.getPlayerData()

            if (db.getPlayerData().isNotEmpty()){
                currentLevel.value = db.getPlayerData()[0].level
                displayCounter.value = db.getPlayerData()[0].expCurrency
            }

            Log.d("test", "findggg $_levelMilestones")
            Log.d("test", "findggg $_levelMilestonesIndex")

        }
    }

    //Adds playerData to the Db.
    fun addPlayerData(playerData: PlayerData){
        GlobalScope.launch(Dispatchers.IO) {
            db.addPlayerData(playerData)
            _playerData.value = db.getPlayerData()
        }
    }

    //Increments the expCurrency.
    fun incrementCount() {
        _playerData.value[0].expCurrency++
    }

    //Returns the players current level.
    fun getCurrentLevel(): Int {
         return currentLevel.value
    }

    //Returns the display-counter so its usable in screens.
    fun getCount():Int{
        return displayCounter.value
    }

    //Used to add level and update _levelMilestone to a new value.
    fun dealWithLevel(){
        _playerData.value = db.getPlayerData()
        _levelMilestonesIndex.value = _playerData.value[0].level

        Log.d("test", "findggg $_levelMilestonesIndex")
        Log.d("test", "findggg ${_levelMilestones.value[_levelMilestonesIndex.value]}")

        if (_playerData.value[0].expCurrency == _levelMilestones.value[_levelMilestonesIndex.value]){
            _playerData.value[0].level++
            addPlayerData(
                PlayerData(
                _playerData.value[0].id,
                    _playerData.value[0].level,
                    _playerData.value[0].baseClickValue,
                    _playerData.value[0].perClickMultiplier,
                    _playerData.value[0].expCurrency
            ))

            _levelMilestonesIndex.value++
        }
    }

}


