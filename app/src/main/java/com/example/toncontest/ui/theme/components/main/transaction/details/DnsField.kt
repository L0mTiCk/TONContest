package com.example.toncontest.ui.theme.components.main.transaction.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Gray
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun DnsField(dns: String) {
    Row(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = MainStrings.transactionDnsTitle,
                fontFamily = robotoFamily,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(vertical = 14.dp, horizontal = 20.dp)
            )
            Text(
                text = dns,
                fontFamily = robotoFamily,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(vertical = 14.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
        Divider(
            color = Light_Gray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}