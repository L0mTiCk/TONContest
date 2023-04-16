package com.example.toncontest.data

import android.util.Log

fun importCheck(): Boolean{
    Log.d("import", Data.importMnemonic.toString())
    Log.d("import", Data.mnemonic0.toString())
    return Data.importMnemonic == Data.mnemonic0
}