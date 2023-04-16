package com.example.toncontest.data.congrats

import android.icu.util.Calendar
import android.util.Log
import com.example.toncontest.data.Data


fun setEntryTime(){
    val calendar = Calendar.getInstance()
    if(Data.entryTime == -1) {
        Data.entryTime = calendar.get(Calendar.MINUTE)
        Log.d("mnemonic", "Set Entry time = ${Data.entryTime}")
    }
}

fun setCurrentTime(){
    val calendar = Calendar.getInstance()
    Data.currentTime = calendar.get(Calendar.MINUTE)
    Log.d("mnemonic", "Set current time = ${Data.currentTime}")
}

fun checkForDelay(): Boolean{
    if(Data.entryTime + Data.delay > Data.currentTime) {
        Log.d("mnemonic", "Delay time = ${Data.entryTime + Data.delay}; current = ${Data.currentTime}")
        return true
    }
    return false
}