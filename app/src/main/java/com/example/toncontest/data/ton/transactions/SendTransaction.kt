package com.example.toncontest.data.ton.transactions

import com.example.toncontest.data.main.send.sendInfo
import com.example.toncontest.data.ton.account.walletV4R2
import com.example.toncontest.data.ton.constants.SendMode
import com.example.toncontest.data.ton.extension.toNano
import org.ton.block.AddrStd
import org.ton.block.Coins
import org.ton.cell.buildCell
import org.ton.contract.wallet.WalletTransfer

suspend fun sendTransaction() {
    walletV4R2.transfer(
        WalletTransfer {
            destination = AddrStd(sendInfo.recipient)
            coins = Coins.ofNano(sendInfo.amount.toNano())
            bounceable = true
            body = buildCell {
                storeUInt(0, 32)
                storeBytes(sendInfo.comment.toByteArray())
            }
            stateInit = null
            sendMode = SendMode.PAY_GAS_SEPARATELY
        }
    )   
}