package com.example.toncontest.data.ton.transactions

import android.util.Log
import com.example.toncontest.data.main.TransactionClass
import com.example.toncontest.data.main.cardList
import com.example.toncontest.data.ton.account.Account
import com.example.toncontest.data.ton.client.liteClient
import com.example.toncontest.data.ton.client.tonMapper
import com.example.toncontest.data.ton.extension.fromNano
import org.ton.bitstring.BitString
import org.ton.block.AddrStd
import org.ton.lite.client.internal.FullAccountState
import org.ton.lite.client.internal.TransactionId
import java.time.Instant
import java.time.ZoneId

suspend fun getTransactions(account: Account) {
    var accountState: FullAccountState
    try {
        Log.d("transactions", "get transaction call")
        cardList.clear()
        accountState = liteClient.getAccountState(accountAddress = AddrStd(account.address))

        var size = 0
        var lastTransactionId = accountState.lastTransactionId!!
        do {
            val txs = liteClient.getTransactions(
                accountAddress = AddrStd(account.address),
                fromTransactionId = lastTransactionId,
                count = 16
            ).map {
                tonMapper.mapTx(it.transaction.value, it.blockId.seqno, it.blockId.workchain)
            }
            size = txs.size
            Log.d("transactions", "Num of transactions = ${txs.size}")
            Log.d("transactions", txs[1].toString())
            for (i in 0..txs.size - 1) {
                val dto = txs[i]
                if (dto.lt == lastTransactionId.lt && cardList.size > 0) {
                    continue
                }
                Log.d("transactions", dto.hash)
                val instant = Instant.ofEpochSecond(dto.created)
                val dateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
                val fee = dto.gasFee.fromNano()
                val minutes =
                    if (dateTime.minute < 10) "0${dateTime.minute}" else dateTime.minute.toString()
                if (dto.inMsg?.op != null || dto.inMsg?.msgType?.inMsg() == true) {
                    cardList.add(
                        TransactionClass(
                            id = dto.hash,
                            amount = dto.inMsg.value.fromNano().toFloat(),
                            isIncome = true,
                            address = dto.inMsg.source!!,
                            fee = fee,
                            message = dto.inMsg.comment ?: "",
                            time = "${dateTime.hour}:${minutes}",
                            date = "${
                                dateTime.dayOfWeek.name.lowercase()
                                    .replaceFirstChar { it.uppercaseChar() }
                            } ${dateTime.dayOfMonth}",
                            year = "${dateTime.year}",
                            actionSucceed = (dto.actionSucceed ?: true)
                        )
                    )
                } else {
                    cardList.add(
                        if (dto.outMsg.isNotEmpty()) {
                            TransactionClass(
                                id = dto.hash,
                                amount = dto.outMsg[0].value.fromNano().toFloat(),
                                isIncome = false,
                                address = dto.outMsg[0].destination!!,
                                fee = fee,
                                message = dto.outMsg[0].comment ?: "",
                                time = "${dateTime.hour}:${minutes}",
                                date = "${
                                    dateTime.dayOfWeek.name.lowercase()
                                        .replaceFirstChar { it.uppercaseChar() }
                                } ${dateTime.dayOfMonth}",
                                year = "${dateTime.year}",
                                actionSucceed = (dto.actionSucceed ?: true)
                            )
                        } else {
                            TransactionClass(
                                id = dto.hash,
                                amount = 0f,
                                isIncome = false,
                                address = dto.accountAddr,
                                fee = fee,
                                message = "",
                                time = "${dateTime.hour}:${minutes}",
                                date = "${
                                    dateTime.dayOfWeek.name.lowercase()
                                        .replaceFirstChar { it.uppercaseChar() }
                                } ${dateTime.dayOfMonth}",
                                year = "${dateTime.year}",
                                actionSucceed = (dto.actionSucceed ?: false)
                            )
                        }
                    )
                }
            }
            //TODO: hash size is 1=216, but this is 512
                //lastTransactionId = TransactionId(txs.first().hash.toByteArray(), txs.last().lt)
                lastTransactionId = TransactionId(BitString(txs.last().hash), txs.last().lt)
                Log.d("transactions", "Generated id = ${lastTransactionId.toString()}, last id = ${accountState.lastTransactionId}")
        } while (size == 16)
    } catch (e: Exception) {
        Log.d("ton", "No transactions")
        Log.d("transactions", e.message.toString())
    }
    Log.d("transactions", "Total transactions num = ${cardList.size}")
}