package com.example.toncontest.data.start

import android.util.Log
import com.example.toncontest.data.Data

fun importCheck(): Boolean{
    Log.d("import", Data.importMnemonic.toString())
    Log.d("import", Data.mnemonic0.toString())
    return Data.importMnemonic == Data.mnemonic0
}