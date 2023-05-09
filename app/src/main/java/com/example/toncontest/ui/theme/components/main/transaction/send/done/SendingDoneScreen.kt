package com.example.toncontest.ui.theme.components.main.transaction.send.done

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.R
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.data.main.send.sendInfo
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.Loader

@Composable
fun SendingDoneScreen() {
    val address = sendInfo.recipient
    val formattedAddress = "${sendInfo.amount} ${MainStrings.sendDoneMain}\n\n" +
            address.substring(0 .. address.length / 2 - 1) + "\n" + address.substring(address.length / 2)
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Loader(res = R.raw.success)
        Text(
            text = MainStrings.sendDoneHeader,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = robotoFamily,
            modifier = Modifier
                .padding(top = 12.dp, end = 12.dp)
        )
        Text(
            text = formattedAddress,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = robotoFamily,
            textAlign = TextAlign.Center
        )
    }
}