package com.example.toncontest.data.main

import kotlin.random.Random


class TransactionClass(val id: Int, val amount: Float, val isIncome: Boolean, val time: String, val address: String, val fee: Double, val message: String = "", val dns: String = "") {

}

var cardList: MutableList<TransactionClass> = mutableListOf()

fun randomCards(){
    val rand = Random
    for (i in 1..20){
        val amount =  rand.nextFloat()
        val isIncome = rand.nextBoolean()
        val time = if (rand.nextBoolean()) "10:23" else "22:51"
        val address = "lhGE49PbJckcU1y70jEQwf6InI414L1PLMIs3rrFx50F"
        val fee = rand.nextDouble(0.0, 0.02)
        val message = "Testing message!"
        cardList.add(TransactionClass(i, amount, isIncome, time, address, fee, message))
    }
    cardList.add(TransactionClass(21, 10.3f, false, "23:22", "address", 0.00002, "custom message", "ton.cpm"))
}

fun returnRandomCards(): Map<*, List<TransactionClass>> {
    randomCards()
    return cardList.groupBy { it.time }
}
