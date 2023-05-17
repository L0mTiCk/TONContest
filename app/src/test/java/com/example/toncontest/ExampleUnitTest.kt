package com.example.toncontest

import com.example.toncontest.data.ton.account.Account
import com.example.toncontest.data.ton.client.TonClient
import com.example.toncontest.data.ton.client.getLiteClient
import com.example.toncontest.data.ton.contracts.wallet.WalletV4R2
import com.example.toncontest.data.ton.mapper.TonMapper
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.block.AddrStd
import org.ton.block.Coins
import org.ton.block.VarUInteger
import org.ton.mnemonic.Mnemonic
import java.lang.Exception

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    fun getAccInfo(tonClient: TonClient, address: String): Coins {
        var balance: Coins
        runBlocking {
            try {
                balance = tonClient.getAccount(AddrStd(address = address))?.storage?.balance?.coins!!
            } catch (e: Exception) {
                balance = Coins(VarUInteger(0))
            }
        }
        println(balance.toString().split("."))
        return balance
    }

    @Test
    fun getAccount() {
        runBlocking {
            //val sharedPreferences = context.getSharedPreferences("MY_APP_PREFERENCES", Context.MODE_PRIVATE)

            var liteClient = getLiteClient()
            var tonMapper = TonMapper()
            var tonClient = TonClient(liteClient = liteClient, tonMapper = tonMapper)

            val m = listOf(
                "wise", "asthma", "goat", "peasant", "stove", "harvest", "fatal", "swim", "enemy", "wrist", "word", "uniform",
                "table", "evidence", "night", "file", "type", "coach", "equal", "rocket", "network", "find", "pluck", "private"
            )

            val mnemonic = m
//                sharedPreferences.getString("MNEMONIC", "")?.split("|")
            val privateKey = PrivateKeyEd25519(Mnemonic.toSeed(mnemonic!!))
            val walletV4R2 = WalletV4R2(0, privateKey, getLiteClient())
            val address = walletV4R2.address.toString(userFriendly = true)
            var balance = getAccInfo(tonClient, address)
            var account = Account(address = address, balance = balance!!)
            println(account.address +  " " + account.balance)
        }
    }
}