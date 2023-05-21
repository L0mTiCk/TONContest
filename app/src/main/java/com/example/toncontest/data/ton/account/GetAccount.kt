package com.example.toncontest.data.ton.account

import android.content.Context
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
    //val sharedPreferences = context.getSharedPreferences("TON_WALLET", Context.MODE_PRIVATE)

    val m = listOf(
        "wise",
        "asthma",
        "goat",
        "peasant",
        "stove",
        "harvest",
        "fatal",
        "swim",
        "enemy",
        "wrist",
        "word",
        "uniform",
        "table",
        "evidence",
        "night",
        "file",
        "type",
        "coach",
        "equal",
        "rocket",
        "network",
        "find",
        "pluck",
        "private"
    )
    val l = listOf(
        "spike", "rifle", "mother", "clown", "crucial", "endorse", "orbit", "music", "slight", "vocal", "ranch", "moon", "author", "million", "appear", "fine", "quiz", "century", "mixture", "blur", "census", "hub", "cereal", "govern"    )

    //TODO: delete my, change to shared
    val mnemonic = m
//                sharedPreferences.getString("MNEMONIC", "")?.split("|")
    val privateKey = PrivateKeyEd25519(Mnemonic.toSeed(mnemonic!!))
    walletV4R2 = WalletV4R2(0, privateKey, liteClient)
    val address = walletV4R2.address.toString(userFriendly = true)
    var balance = getAccInfo(tonClient, address)
    account = Account(address = address, balance = balance!!)
    println(account.address + " " + account.balance)

    getTransactions(account)

    return account
}