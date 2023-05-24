package com.example.toncontest.data.ton.account

import android.content.Context
import android.util.Log
import com.example.toncontest.data.Data
import com.example.toncontest.data.ton.client.TonClient
import com.example.toncontest.data.ton.client.liteClient
import com.example.toncontest.data.ton.client.tonClient
import com.example.toncontest.data.ton.contracts.wallet.WalletV4R2
import com.example.toncontest.data.ton.transactions.getTransactions
import kotlinx.coroutines.runBlocking
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.block.AddrStd
import org.ton.block.Coins
import org.ton.block.VarUInteger
import org.ton.mnemonic.Mnemonic

class Account(var address: String, var balance: Coins) {

}

var account = Account("", Coins(VarUInteger(0)))

suspend fun getAccInfo(tonClient: TonClient, address: String): Coins {

    var balance: Coins
    runBlocking {
        try {
            balance = tonClient.getAccount(AddrStd(address = address))?.storage?.balance?.coins!!
        } catch (e: Exception) {
            balance = Coins(VarUInteger(0))
        }
    }
    return balance
}

lateinit var walletV4R2: WalletV4R2

suspend fun getAccount(context: Context): Account {
    try {
        val sharedPreferences = context.getSharedPreferences("TON_WALLET", Context.MODE_PRIVATE)
        val mnemonic = sharedPreferences.getString("MNEMONIC", "")?.split("|")
        val privateKey = PrivateKeyEd25519(Mnemonic.toSeed(mnemonic!!))
        walletV4R2 = WalletV4R2(0, privateKey, liteClient)
        val address = walletV4R2.address.toString(userFriendly = true)
        val balance = getAccInfo(tonClient, address)
        account = Account(address = address, balance = balance)
        Log.d("transactions", mnemonic.toString())
        Log.d("transactions", "Data.importMnemonic = " + Data.importMnemonic.toString())
        Log.d("transactions", account.address + " " + account.balance)
        walletV4R2.createStateInit()
        getTransactions(account)

        return account
    } catch (e: Exception) {
        account = Account(address = "aaaabbbbccccaaaabbbbbccccaaaabbbbccccaaaabbbbcccc", balance = Coins(
            VarUInteger(0)
        ))
        return account
    }
}