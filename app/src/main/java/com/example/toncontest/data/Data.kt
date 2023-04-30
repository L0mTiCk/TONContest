package com.example.toncontest.data

import com.example.toncontest.data.testing.mnemonicRandom

object Data {
    var isBiometric = false
    var isFirstLaunch = true

    val testMnemonic = mutableListOf(
        "alfa",
        "beta",
        "wolf",
        "computer",
        "man",
        "angel",
        "truck",
        "clown",
        "oxygen",
        "gamma",
        "crate",
        "box",
        "apple",
        "cherry",
        "pop",
        "balloon",
        "mixer",
        "banana",
        "potato",
        "desk",
        "oak",
        "pine",
        "burch",
        "dirt"
    )
    var mnemonic0: MutableList<String> = testMnemonic

    //start screen
    val startHeaderText = "TON Wallet"
    val startMainText = "TON Wallet allows you to make fast and\n" +
            "secure blockchain-based payments\n" +
            "without intermediaries."

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
    //set delay for alert in minutes
    const val delay = 1
    var entryTime = -1
    var currentTime = 0

    //test time data
    var testTimeMainText = "Let’s check that you wrote them down correctly. Please enter the words \n" +
            "${mnemonicRandom[0]}, ${mnemonicRandom[1]} and ${mnemonicRandom[2]}."
    val testTimeHeaderText = "Test time!"
    val testAlertTitle = "Incorrect words"
    val testAlertText = "The secret words you have entered\ndo not match the ones in the list."
    val testWordsButtonText = "See words"
    val testAgainButtonText = "Try again"

    //success screen
    val successMainText = "Now set up a passcode to secure\ntransactions."
    val successHeaderText = "Perfect!"
    val successButtonText = "Set a Passcode"
    val checkBoxText = "Enable Biometric Auth"

    //passcode screen
    var passcodeForConfirm = mutableListOf<Int>()
    var passcodeDigitNum = 4
    val setPasscodeHeaderText = "Set a Passcode"
    val passcodeMainText = "Enter the $passcodeDigitNum digits in the passcode"
    val confirmPasscodeHeaderText = "Confirm a Passcode"
    val passcodeButtonText = "Passcode options"
    val numsForButtons = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, -1, 0, -1)
    val lettersForButtons = listOf("", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ", "", "+")

    //import screen
    var importMnemonic = MutableList<String>(24) {""}
    val importAlertTitle = "Incorrect words"
    val importAlertText = "Sorry, you have entered incorrect secret words. Please double check and try again."
    val importAlertButtonText = "OK"
    val importHeaderText = "24 Secret Words"
    val importMainText = "You can restore access to your wallet by entering 24 words you wrote when down you creating the wallet."
    val importButtonText = "Continue"
    val importNoMnemonicText = "I don’t have those"

    //successImport screen
    val successImportMainText = "Your wallet has just\nbeen imported!"

    //no mnemonic screen
    val noMnemonicHeaderText = "Too bad!"
    val noMnemonicMainText = "Without the secret words you can’t restore\naccess to the wallet."
    val noMnemonicImportButtonText = "Enter 24 secret words"
    val noMnemonicCreateButtonText = "Create a new empty wallet instead"

    //done screen
    val doneHeaderText = "Ready to go!"
    val doneMainText = "You are all set. Now you have a wallet that\n" +
            "only you control — directly, without\n" +
            "middlemen or bankers. "
    val doneButtonText = "View my wallet"
}