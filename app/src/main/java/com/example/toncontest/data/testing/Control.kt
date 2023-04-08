package com.example.toncontest.data.testing

import com.example.toncontest.data.Data
import kotlin.random.Random
import kotlin.random.nextInt

var mnemonicRandom: MutableList<Int> = mutableListOf(0, 0, 0)
var leftScreen = false

fun genRandomTesting(){
    val random = Random(System.currentTimeMillis())
    for(i in 0..2){
        var rand = random.nextInt(1, 25)
        if (mnemonicRandom.contains(rand))
            rand = random.nextInt(1, 25)
        mnemonicRandom[i] = rand
    }
    Data.testTimeMainText = "Let’s check that you wrote them down correctly. Please enter the words \n" +
            "${mnemonicRandom[0]}, ${mnemonicRandom[1]} and ${mnemonicRandom[2]}."
}

fun checkRandom(){
    if(leftScreen)
        genRandomTesting()
    else if (mnemonicRandom.contains(0))
        genRandomTesting()
}

fun chekCorectness(str1: String, str2: String, str3: String){

}