package com.example.gameappdev

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class GameInfo() {
    val counter: MutableState<Int> = mutableStateOf(0)
    val radix: MutableState<Int> = mutableStateOf(10)

    fun setRad(num: Int){
        radix.value = num
    }
}