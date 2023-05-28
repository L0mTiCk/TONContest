package com.example.toncontest.data.ton.client

import GLOBAL_CONFIG
import TESTNET_CONFIG
import com.example.toncontest.data.ton.mapper.TonMapper
import kotlinx.coroutines.Dispatchers
import org.ton.lite.client.LiteClient

var liteClient = LiteClient(
    coroutineContext = Dispatchers.Default,
    //liteClientConfigGlobal = GLOBAL_CONFIG
    liteClientConfigGlobal = TESTNET_CONFIG
)

var tonMapper = TonMapper()

var tonClient = TonClient(
    liteClient = liteClient,
    tonMapper = tonMapper
)