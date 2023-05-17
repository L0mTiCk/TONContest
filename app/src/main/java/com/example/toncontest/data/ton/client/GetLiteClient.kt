package com.example.toncontest.data.ton.client

import GLOBAL_CONFIG
import TESTNET_CONFIG
import kotlinx.coroutines.Dispatchers
import org.ton.lite.client.LiteClient

suspend fun getLiteClient(): LiteClient {
    var liteClient = LiteClient(
        coroutineContext = Dispatchers.Default,
        liteClientConfigGlobal = TESTNET_CONFIG
    )
    return liteClient
}