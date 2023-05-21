package com.example.toncontest.ui.theme.screens.main.send

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.R
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
    var amount by remember { mutableStateOf("-1") }
    var checked by remember { mutableStateOf(false) }
    var numColor = animateColorAsState(targetValue = if ((amount.toDouble()) > balance) TonRed else Color.Black)
    //var numColor = animateColorAsState(targetValue = Color.Black)
    var overflowAlpha = animateFloatAsState(targetValue = if (amount.toDouble() > balance) 1f else 0f)
    //var overflowAlpha = animateFloatAsState(targetValue = 1f)


    LaunchedEffect(key1 = amount) {
        Log.d("amountSend", amount)
        sendInfo.amount = amount.toDouble()
    }

    fun buildAnnotatedStringWithNums(text: String): AnnotatedString {
        val builder = AnnotatedString.Builder()


        var splitStr = text.split(".")
        Log.d("amountSend", "split str = " + splitStr.toString())
        Log.d("amountSend",  "Temp str = " + text)
        if (splitStr.size == 1) {
            builder.withStyle(
                SpanStyle(
                    color = numColor.value,
                    fontFamily = robotoFamily,
                    fontSize = 44.sp
                )
            ) {
                if (text == "-1") {
                    append("")
                } else {
                    append(text)
                }
            }
        }
        else if (splitStr.size > 1) {
            builder.withStyle(
                SpanStyle(
                    color = numColor.value,
                    fontSize = 44.sp,
                    fontFamily = robotoFamily
                )
            ) {
                append(splitStr[0])
            }
            builder.withStyle(
                SpanStyle(
                    color = numColor.value,
                    fontSize = 32.sp,
                    fontFamily = robotoFamily
                )
            ) {
                append("." + splitStr[1])
            }
        }
        return builder.toAnnotatedString()
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
                    modifier = Modifier.fillMaxSize().weight(2f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.size(44.dp))
                        Image(
                            painter = painterResource(id = R.drawable.diamond),
                            contentDescription = "Diamond",
                            modifier = Modifier.size(44.dp)
                        )
                        TextField(
                            value = if (amount == "-1") "" else amount,
                            onValueChange = { it ->
                                var temp = it.replace(',', '.')
                                temp = temp.replace("..", ".")
                                if (temp.count { it == '.' } <= 1) {
                                    if (temp == ".")
                                        temp = "0."
                                    else if (it == "") {
                                        temp = (-1).toString()
                                    }
                                    amount = temp
                                }
                                Log.d("amountSend", "Str from field = $temp")
                            },
                            visualTransformation = {
                                TransformedText(
                                    buildAnnotatedStringWithNums(amount),
                                    offsetMapping = OffsetMapping.Identity
                                )
                            },
                            singleLine = true,
                            placeholder = {
                                Text(
                                    text = "0",
                                    color = Light_Gray,
                                    fontFamily = robotoFamily,
                                    fontSize = 44.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .widthIn(1.dp, Dp.Infinity),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                cursorColor = Light_Blue,
                                backgroundColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
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
                    .padding(top = 30.dp, start = 20.dp, bottom = 16.dp, end = 20.dp)
                    .fillMaxWidth()
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
                                amount = balance.toString()
                            } else {
                                amount = 0.0.toString()
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
            }
        }
    }
}