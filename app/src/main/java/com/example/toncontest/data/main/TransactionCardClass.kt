package com.example.toncontest.data.main

import java.math.BigDecimal


class TransactionClass(
    val id: String, val amount: Float, val isIncome: Boolean,
    val address: String, val fee: BigDecimal, val message: String = "", val dns: String = "",
    val date: String = "", val time: String, val year: String, val actionSucceed: Boolean) {

}

var cardList: MutableList<TransactionClass> = mutableListOf()
