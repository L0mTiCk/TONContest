package com.example.toncontest.ui.theme.screens.main.send

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.R
import com.example.toncontest.data.Data
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.data.main.send.sendInfo
import com.example.toncontest.data.ton.account.account
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.Light_Gray
import com.example.toncontest.ui.theme.TonGray
import com.example.toncontest.ui.theme.TonRed
import com.example.toncontest.ui.theme.components.main.transaction.send.ContinueButton
import com.example.toncontest.ui.theme.components.main.transaction.send.SendCardHeader
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun SendAmountScreen(navController: NavController) {
    val address = sendInfo.recipient
    val dns = "andre.ton"
    val isDns = true
    val balance = account.balance.toString().toDouble()
    var amount by remember { mutableStateOf(0.0) }
    var amountStr by remember { mutableStateOf(mutableListOf("0", "0")) }
    var checked by remember { mutableStateOf(false) }
    var numColor = animateColorAsState(targetValue = if (amount > balance) TonRed else Color.Black)
    var overflowAlpha = animateFloatAsState(targetValue = if (amount > balance) 1f else 0f)

    LaunchedEffect(key1 = amount) {
        amountStr = amount.toString().split(".") as MutableList<String>
        sendInfo.amount = amount
    }

    @Composable
    fun PasscodeKeyboard() {

        @Composable
        fun NumButton(index: Int) {
            var backColor = if (index != -2) Light_Gray else Color.Transparent
            Button(
                onClick = {
                    checked = false
                    amount -= amount % 1
                    amount = amount * 10 + index
                },
                modifier = Modifier
                    .width(109.dp)
                    .height(47.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = backColor
                ),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp)
            ) {
                Text(
                    text = if (index != -1) index.toString() else "",
                    fontFamily = robotoFamily,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.width(45.dp)
                )
                Text(
                    text =
                    if (index != -1 && index != 0)
                        Data.lettersForButtons[index]
                    else if (index != -1 && index == 0)
                        Data.lettersForButtons[11]
                    else "",
                    fontFamily = robotoFamily,
                    fontSize = 14.sp,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        @Composable
        fun BackspaceButton() {
            Button(
                onClick = {
                    amount = (amountStr[0].toInt().div(10)).toDouble()
                    checked = false
                },
                modifier = Modifier
                    .width(109.dp)
                    .height(47.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Light_Gray
                ),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.backspace),
                        contentDescription = "Test"
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                itemsIndexed(Data.numsForButtons) { index, item ->
                    if (index != 9 && index != 11)
                        NumButton(index = item)
                    else if (index == 11)
                        BackspaceButton()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .padding(top = 2.dp)
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f)
                    .fillMaxSize()
            ) {
                SendCardHeader(navController = navController)
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    color = TonGray
                                )
                            ) {
                                append("Send to: ")
                            }
                            withStyle(
                                SpanStyle(
                                    color = Color.Black
                                )
                            ) {
                                append(
                                    address.substring(
                                        0,
                                        4
                                    ) + "..." + address.substring(address.length - 4)
                                )
                            }
                            if (isDns) {
                                withStyle(
                                    SpanStyle(
                                        color = TonGray
                                    )
                                ) {
                                    append(" $dns")
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Edit",
                        color = Light_Blue,
                        fontFamily = robotoFamily,
                        modifier = Modifier
                            .indication(MutableInteractionSource(), null)
                            .clickable {
                                navController.popBackStack()
                            },
                        textAlign = TextAlign.End
                    )
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.diamond),
                            contentDescription = "Diamond",
                            modifier = Modifier.size(44.dp)
                        )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            color = if (amountStr[0] == "0") Light_Gray else numColor.value,
                                            fontSize = 44.sp,
                                            fontFamily = robotoFamily,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) {
                                        append(amountStr[0])
                                    }
                                    if (amountStr[1] != "0") {
                                        withStyle(
                                            SpanStyle(
                                                color = numColor.value,
                                                fontSize = 32.sp,
                                                fontFamily = robotoFamily,
                                                fontWeight = FontWeight.Medium
                                            )
                                        ) {
                                            append("." + amountStr[1])
                                        }
                                    }
                                }
                            )
                    }
                    Text(
                        text = MainStrings.sendBiggerAmount,
                        color = numColor.value,
                        fontFamily = robotoFamily,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(overflowAlpha.value),
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, bottom = 16.dp, end = 20.dp)
                    .weight(1.3f)
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 53.dp)
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = MainStrings.sendAllText,
                        color = Color.Black
                    )
                    Image(
                        painter = painterResource(id = R.drawable.diamond),
                        contentDescription = "Diamond",
                        modifier = Modifier
                            .size(18.dp)
                    )
                    Text(
                        text = balance.toString(),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                            if (checked) {
                                amount = balance
                            } else {
                                amount = 0.0
                            }
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Light_Blue,
                            checkedTrackColor = Light_Blue,
                            checkedTrackAlpha = 0.8f,
                            uncheckedThumbColor = Color.LightGray,
                            uncheckedTrackColor = Color.LightGray
                        )
                    )
                }
                ContinueButton(navController = navController, error = {}, route = "sendConfirm", 2)
                Spacer(modifier = Modifier.height(16.dp))
                PasscodeKeyboard()
            }
        }
    }
}