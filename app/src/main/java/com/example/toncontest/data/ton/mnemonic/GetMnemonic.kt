package com.example.toncontest.data.ton.mnemonic

import android.content.Context
import com.example.toncontest.data.Data

fun getMnemonic(context: Context): List<String> {
    val sharedPreferences = context.getSharedPreferences("TON_WALLET", Context.MODE_PRIVATE)
    val mnemonicString = sharedPreferences.getString("MNEMONIC", null)
    if (mnemonicString != null) {
        Data.mnemonic0 = mnemonicString.split("|")
        return mnemonicString.split("|")
    } else {
        val mnemonic = genMnemonic()
        Data.mnemonic0 = mnemonic
        sharedPreferences.edit().putString("MNEMONIC", mnemonic.joinToString("|")).apply()
        return mnemonic
    }
}