package com.example.toncontest.data.main.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class Values(
    var usd: Double,
    var rub: Double,
    var eur: Double
) {
    fun getValue(balance: Double, currency: String): Double {
        when (currency) {
            "usd" -> return usd * balance
            "rub" -> return rub * balance
            "eur" -> return eur * balance
            else -> return usd * balance
        }
    }
}

var values = Values(0.0, 0.0, 0.0)

fun getValues() {
    var url = URL("https://api.coingecko.com/api/v3/simple/price?ids=the-open-network&vs_currencies=usd%2Crub%2Ceur")
    CoroutineScope(Dispatchers.IO).launch {
        val connection = url.openConnection()
        val inputStream = connection.getInputStream()
        val jsonText = inputStream.bufferedReader().use { it.readText() }
        var unsorted = jsonText.split("{")[2].split(",")
        var temp = mutableListOf<Double>()
        unsorted.forEach {
            temp.add(it.split(":")[1].replace("}}", "").toDouble())
        }
        values.usd = temp[0]
        values.rub = temp[1]
        values.eur = temp[2]
    }
}
