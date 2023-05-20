package com.example.toncontest.ui.theme.components.main.transaction.send

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.data.main.cardList
import com.example.toncontest.ui.theme.TonGray
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun RecentTransaction(address: (String) -> Unit) {
    val lastTransaction = cardList.first()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .clickable {
                address(lastTransaction.address)
            }
    ) {
        Text(
            text = lastTransaction.address,
            fontSize = 16.sp,
            fontFamily = robotoFamily,
            modifier = Modifier
                .padding(top = 13.dp)
        )
        Text(
            text = lastTransaction.date,
            fontSize = 16.sp,
            fontFamily = robotoFamily,
            color = TonGray,
            modifier = Modifier
                .padding(top = 2.dp, bottom = 13.dp)
        )
    }
}