package com.example.toncontest.data.start

import com.example.toncontest.data.Data
import com.example.toncontest.data.ton.account.checkAccount
import com.example.toncontest.data.ton.client.liteClient
import com.example.toncontest.data.ton.contracts.wallet.WalletV4R2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.mnemonic.Mnemonic

suspend fun importCheck(): Boolean {
    val mnemonic = Data.importMnemonic
    if (Mnemonic.isValid(mnemonic)) {
        val privateKey = PrivateKeyEd25519(Mnemonic.toSeed(mnemonic))
        val testWalletV4R2 = WalletV4R2(0, privateKey, liteClient)
        val address = testWalletV4R2.address.toString(userFriendly = true)
        var isValid = false
        CoroutineScope(Dispatchers.IO).async {
            isValid = checkAccount(address)
        }.await()
        return isValid
    } else {
        return false
    }
}