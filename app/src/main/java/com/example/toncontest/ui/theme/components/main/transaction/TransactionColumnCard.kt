package com.example.toncontest.ui.theme.components.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.R
import com.example.toncontest.ui.theme.Light_Gray
import com.example.toncontest.ui.theme.TonGreen
import com.example.toncontest.ui.theme.TonRed
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun TransactionColumnCard (amount: Float, isIncome: Boolean, time: String, address: String, fee: String, message: String, onClick: () -> Unit) {
    val textColor = if (isIncome) TonGreen else TonRed
    val amountString = amount.toString().split('.')
    Row(modifier = Modifier
        .padding(top = 0.dp)
        .fillMaxWidth()
        .clickable {
            onClick()
        }
    ) {
        Column(modifier = Modifier.weight(9f)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.diamond),
                    contentDescription = "Diamond"
                )
                Text(modifier = Modifier
                    .padding(start = 3.dp, end = 3.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = textColor,
                                fontSize = 18.sp,
                                fontFamily = robotoFamily,
                            )
                        ) {
                            append(amountString[0])
                        }
                        if (amountString.size > 1) {
                            withStyle(
                                style = SpanStyle(
                                    color = textColor,
                                    fontSize = 14.sp,
                                )
                            ) {
                                append("." + amountString[1])
                            }
                        }
                    }
                )
                Text(
                    text = if (isIncome) "from" else "to",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = robotoFamily
                )
            }
            Text(
                text = address.substring(0, 6) + "..." + address.substring(address.length - 6),
                fontFamily = robotoFamily,
                fontSize =  14.sp,
                modifier = Modifier
                    .padding(bottom = 0.dp)
            )
            Text(
                text = "-$fee storage fee",
                fontFamily = robotoFamily,
                fontSize =  14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(bottom = 6.dp)
            )
            if (message.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .background(
                            color = Light_Gray,
                            shape = RoundedCornerShape(topStart = 4.dp, topEnd = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp)
                        )
                ) {
                    Text(
                        text = message,
                        fontSize = 15.sp,
                        fontFamily = robotoFamily,
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                    )
                }
            }
        }
        Column(Modifier.weight(2f), horizontalAlignment = Alignment.End) {
            Text(
                text = time,
                fontSize = 14.sp,
                fontFamily = robotoFamily
            )
        }
    }
    Divider(color = Light_Gray, thickness = 1.dp, modifier = Modifier.padding(top = 16.dp, bottom = 14.dp).fillMaxWidth())
}