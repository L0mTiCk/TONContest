package com.example.toncontest.data.ton.transactions

import com.example.toncontest.data.main.send.sendInfo

fun checkAddress(): Boolean {
    //TODO: check address in ton, check if dns
    if (sendInfo.recipient.length < 48 || sendInfo.recipient.contains(".ton")) {
        return false
    }
    else {
        return true
    }
}