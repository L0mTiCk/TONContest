package com.example.toncontest.data.ton.account

import com.example.toncontest.data.ton.client.tonClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.ton.block.AddrStd

suspend fun checkAccount(address: String): Boolean {
    try {
        CoroutineScope(Dispatchers.IO).async {
            val accState = tonClient.getAccount(AddrStd(address))
        }.await()
        return true
    } catch (e: Exception) {
        if (e.cause?.message?.contains("boc", ignoreCase = true) == true)
            return true
        return false
    }
}