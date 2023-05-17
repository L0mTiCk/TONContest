package com.example.toncontest.data.ton.mnemonic

import kotlinx.coroutines.runBlocking
import org.ton.mnemonic.Mnemonic

fun genMnemonic(): List<String> {
    var mnemonic: List<String>
    runBlocking {
        mnemonic = Mnemonic.generate()
    }
    return mnemonic
}
