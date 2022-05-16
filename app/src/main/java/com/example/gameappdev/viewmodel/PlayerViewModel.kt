package com.example.gameappdev.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameappdev.database.DataApplication
import com.example.gameappdev.database.PlayerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(context: Application): AndroidViewModel(context) {
    private var _levelMilestonesIndex:MutableState<Int> = mutableStateOf(0)
    private val _levelMilestones: MutableState<List<Int>> = mutableStateOf(listOf(50,100,200,400,800,1600,3200,6400,1))

    private var _baseClickMilestonesIndex:MutableState<Int> = mutableStateOf(0)
    private val _baseClickMilestones: MutableState<List<Int>> = mutableStateOf(listOf(100,200,400,800,1600,3200,6400,12800,1))

    private var _multiMilestonesIndex:MutableState<Int> = mutableStateOf(0)
    private val _multiMilestones: MutableState<List<Int>> = mutableStateOf(listOf(200,400,800,1600,3200,6400,12800,25600,1))

    private val _currentLevel: MutableState<Int> = mutableStateOf(0)
    var currentLevel: MutableState<Int> = _currentLevel

    private val _displayCounter: MutableState<Int> =  mutableStateOf(0)
    var displayCounter: MutableState<Int> = _displayCounter

    private val _playerData: MutableState<List<PlayerData>> = mutableStateOf(listOf())
    val playerData: MutableState<List<PlayerData>> = _playerData

    val db = DataApplication(context).database.playerDataDao()



    //Grabs currentLevel and displayCounter for newGameScreen()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (db.getPlayerData().isNotEmpty()){
                currentLevel.value = db.getPlayerData()[0].level
                displayCounter.value = db.getPlayerData()[0].expCurrency
            }
        }
    }

    //Ensures data in _playerData.
    fun updateEmpty(){
        viewModelScope.launch(Dispatchers.IO) {
            _playerData.value = db.getPlayerData()
            playerData.value = _playerData.value
            Log.d("test", "findggg ${playerData.value}")
        }

    }

    //Adds playerData to the Db.
    fun addPlayerData(playerData: PlayerData){
        viewModelScope.launch(Dispatchers.IO) {
            db.addPlayerData(playerData)
            _playerData.value = db.getPlayerData()
        }
    }

    //Increments the expCurrency.
    fun incrementCount() {
        _playerData.value[0].expCurrency = _playerData.value[0].expCurrency + (_playerData.value[0].baseClickValue * _playerData.value[0].perClickMultiplier)
    }

    //Returns the players current level.
    fun getCurrentLevel(): Int {
        return _playerData.value[0].level
    }

    //Returns the display-counter so its usable in screens.
    fun getCount():Int{
        return displayCounter.value
    }

    //Used to add level and update _levelMilestone to a new value.
    fun dealWithLevel(){
        _playerData.value = db.getPlayerData()
        _levelMilestonesIndex.value = _playerData.value[0].level-1

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

    fun getBaseClickUpgradePrice():Int{
        _baseClickMilestonesIndex.value = _playerData.value[0].baseClickValue-1
        return _baseClickMilestones.value[_baseClickMilestonesIndex.value]
    }

    //Adds 1 to baseClick Multi when upgrade button is clicked.
    //Subtracts the amount of the upgrade.
    //Adds values baseClickValue to DB.
    fun dealWithBaseClickUpgrade(){
        viewModelScope.launch(Dispatchers.IO) {
            _playerData.value = db.getPlayerData()
            _baseClickMilestonesIndex.value = _playerData.value[0].baseClickValue-1

            _playerData.value[0].baseClickValue++
            _playerData.value[0].expCurrency -= _baseClickMilestones.value[_baseClickMilestonesIndex.value]
            displayCounter.value = _playerData.value[0].expCurrency
            addPlayerData(
                PlayerData(
                    _playerData.value[0].id,
                    _playerData.value[0].level,
                    _playerData.value[0].baseClickValue,
                    _playerData.value[0].perClickMultiplier,
                    _playerData.value[0].expCurrency
                )
            )

            _baseClickMilestonesIndex.value++
        }

    }

    //Returns the current multi upgrade price.
    fun getMultiUpgradePrice():Int{
        _multiMilestonesIndex.value = _playerData.value[0].perClickMultiplier-1
        return _multiMilestones.value[_multiMilestonesIndex.value]
    }

    //Adds 1 to perClickMulti when upgrade button is clicked.
    //Subtracts the amount of the upgrade.
    //Adds values perClickMulti to DB.
    fun dealWithMultiUpgrade(){
        viewModelScope.launch(Dispatchers.IO) {
            _playerData.value = db.getPlayerData()
            _multiMilestonesIndex.value = _playerData.value[0].perClickMultiplier-1

            _playerData.value[0].perClickMultiplier++
            _playerData.value[0].expCurrency -= _multiMilestones.value[_multiMilestonesIndex.value]
            displayCounter.value = _playerData.value[0].expCurrency
            addPlayerData(
                PlayerData(
                    _playerData.value[0].id,
                    _playerData.value[0].level,
                    _playerData.value[0].baseClickValue,
                    _playerData.value[0].perClickMultiplier,
                    _playerData.value[0].expCurrency
                )
            )

            _multiMilestonesIndex.value++
        }

    }

    //Used by Upgrade() to check if enough exp to buy baseClick upgrade.
    fun checkBaseAmount():Boolean{
        return _playerData.value[0].expCurrency >= _baseClickMilestones.value[_baseClickMilestonesIndex.value]
    }

    //Used by Upgrade() to check if enough exp to buy multi upgrade.
    fun checkMultiAmount():Boolean{
        return _playerData.value[0].expCurrency >= _multiMilestones.value[_multiMilestonesIndex.value]
    }

    fun dealWithIntent(): Intent {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Try to beat my level on this new bit clicker game! Download Here: https://download.bitclicker.io"
            )
            type = "text/plain"
        }

        return Intent.createChooser(sendIntent, null)

    }

}


