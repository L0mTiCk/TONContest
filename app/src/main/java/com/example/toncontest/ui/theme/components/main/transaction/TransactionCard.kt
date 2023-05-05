package com.example.toncontest.ui.theme.components.main.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.R
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.data.main.cardList
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.Light_Gray
import com.example.toncontest.ui.theme.TonGray
import com.example.toncontest.ui.theme.TonGreen
import com.example.toncontest.ui.theme.TonRed
import com.example.toncontest.ui.theme.components.main.transaction.details.DetailsAddressField
import com.example.toncontest.ui.theme.components.main.transaction.details.DnsField
import com.example.toncontest.ui.theme.components.main.transaction.details.SendToAddressButton
import com.example.toncontest.ui.theme.components.main.transaction.details.TransactionIdField
import com.example.toncontest.ui.theme.components.main.transaction.details.ViewInExplorerField
import com.example.toncontest.ui.theme.robotoFamily


@Composable
fun TransactionCard(onDrag: (Boolean) -> Unit, transactionId: Int) {
    val transaction = cardList[transactionId]
    val amountColor = if (transaction.isIncome) TonGreen else TonRed
    val amountString = transaction.amount.toString().split('.')
    val addressType = if (transaction.isIncome) MainStrings.transactionCardSenderTitle else MainStrings.transactionCardRecipientTitle
    val address = transaction.address
    val dns = transaction.dns
    var status by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .draggable(
                    state = rememberDraggableState(onDelta = {}),
                    onDragStopped = { velocity ->
                        if (velocity > 0)
                            onDrag(false)
                    },
                    orientation = Orientation.Vertical
                ),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = MainStrings.transactionCardHeader,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = robotoFamily,
                modifier = Modifier
                    .padding(start = 20.dp, top = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.diamond), contentDescription = "Diamond", modifier = Modifier
                    .size(44.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = amountColor,
                                fontSize = 44.sp,
                                fontFamily = robotoFamily,
                            )
                        ) {
                            append(amountString[0])
                        }
                        if (amountString.size > 1) {
                            withStyle(
                                style = SpanStyle(
                                    color = amountColor,
                                    fontSize = 32.sp,
                                    fontFamily = robotoFamily
                                )
                            ) {
                                append(".${amountString[1]}")
                            }
                        }
                }

                )
            }
            Text(
                text = "${transaction.fee.toBigDecimal().toPlainString()} transaction fee",
                color = TonGray,
                fontSize = 15.sp,
                fontFamily = robotoFamily,
                modifier = Modifier.padding(top = 4.dp)
            )
            when (status) {
                -1 -> {
                    Text(
                        text = "Canceled",
                        color = TonRed,
                        fontFamily = robotoFamily,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                    )
                }
                0 -> {
                    Row(
                        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(10.dp),
                            strokeWidth = 1.dp,
                            color = Light_Blue
                        )
                        Text(
                            text = "Pending",
                            color = Light_Blue,
                            fontFamily = robotoFamily,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
                1-> {
                    Text(
                        text = "(TODO: date) at ${transaction.time}",
                        color = TonGray,
                        fontSize = 15.sp,
                        fontFamily = robotoFamily,
                        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                    )
                }
            }

            //message
            if (transaction.message.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .background(
                            color = Light_Gray,
                            shape = RoundedCornerShape(
                                topStart = 4.dp,
                                topEnd = 10.dp,
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp
                            )
                        )
                        .widthIn(150.dp, 300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = transaction.message,
                        fontSize = 15.sp,
                        fontFamily = robotoFamily,
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                    )
                }
            }
            Text(
                text = MainStrings.transactionDetailsHeader,
                color = Light_Blue,
                fontFamily = robotoFamily,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 32.dp, start = 20.dp)
                    .fillMaxWidth()
            )
            if (!transaction.isIncome) {
                DnsField(dns = dns)
            }
            DetailsAddressField(addressType = addressType, address = address)
            //TODO: change to real transaction id!!
            TransactionIdField(id = "7HxFi5…JpHcU=")
            ViewInExplorerField()
            SendToAddressButton(address = address)
        }
    }
}