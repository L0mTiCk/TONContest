package com.example.toncontest.data

import com.example.toncontest.data.testing.mnemonicRandom

object Data {

    var mnemonic0 = MutableList<String>(24) {""}

    // Congratulations screen data
    val congratsMainText = "Your TON Wallet has just been created. \n" +
            "Only you control it.\n" +
            "\n" +
            "To be able to always have access to it, please write down secret words and set up a secure passcode."
    val congratsHeaderText = "Congratulations"

    //recovery phrase screen data
    val recoveryMainText = "Write down these 24 words in this exact order and keep them in a secure place. Do not share this list with anyone. If you lose it, you will irrevocably lose access to your TON account."
    val recoveryHeaderText = "Your recovery phrase"
    val alertTitle = "Sure done?"
    val alertText = "You didn’t have enough time to\n" +
            "write these words down."
    val okButtonText = "OK, sorry"
    val skipButtonText = "Skip"
    val testMnemon = listOf(
        "ds",
        "angel",
        "wolf",
        "computer",
        "man",
        "angel",
        "wolf",
        "computer",
        "idk",
        "angel",
        "wolf",
        "computer",
        "idk",
        "computer",
        "computer",
        "computer",
        "computer",
        "computer",
        "computer",
        "computer",
        "computer",
        "computer",
        "computer",
        "computer"
    )
    //set delay for alert in minutes
    const val delay = 1
    var entryTime = -1
    var currentTime = 0

    //test time data
    var testTimeMainText = "Let’s check that you wrote them down correctly. Please enter the words \n" +
            "${mnemonicRandom[0]}, ${mnemonicRandom[1]} and ${mnemonicRandom[2]}."
    val testTimeHeaderText = "Test time!"
}