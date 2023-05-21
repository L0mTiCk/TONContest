package com.example.toncontest.ui.theme.components.main.transaction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.toncontest.data.main.TransactionClass
import com.example.toncontest.ui.theme.robotoFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionColumn(returnId: (result: String?) -> Unit, cards: MutableList<TransactionClass>) {
    val grouped = cards.groupBy { it.date }
    LazyColumn(
        modifier = Modifier
            .padding(top = 20.dp, start = 12.dp, end = 12.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        ) {
        grouped.forEach { (initial, cards) ->

            stickyHeader {
                Row(Modifier.background(Color.White).fillMaxWidth()) {
                    Text(
                        text = "$initial",
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                    )
                }
            }
            items (cards) { card ->
                TransactionColumnCard(
                    amount = card.amount,
                    isIncome = card.isIncome,
                    time = card.time,
                    address = card.address,
                    fee = card.fee.toPlainString(),
                    message = card.message,
                    onClick = {
                        returnId(card.id)
                    }
                )
            }
        }
    }
}